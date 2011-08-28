(ns jobboard.web.core
  (:use [jobboard.web.layout :only (layout css)]
        [gaka.core :only (save-css)]
        [ring.util.response :only (file-response redirect-after-post)])
  (:require [jobboard.web.home :as home]
            [jobboard.web.post-job :as post-job]
            [jobboard.web.job :as job]
            [jobboard.db :as db])
  (:import java.io.File
           java.util.Date))


(defn get-css [path]
  (save-css path css home/css post-job/css job/css)
  (File. path))

(defn post-job
  ([] (post-job ""))
  ([id]
     (let [params (db/get-job id)]
       (layout (post-job/content params)))))


(defmulti post-preview-job (fn [params] [(contains? params :_id) (:action params)]))

(defmethod post-preview-job [false nil] [params]
  (let [id (:_id (db/upsert-job! params))]
    (redirect-after-post (str "/preview-job-post/" id))))


(defmethod post-preview-job [true nil] [params]
  (if-let [job (db/get-job (:_id params))]
    (if (not (:published job))
      (do 
        (db/upsert-job! job params)
        (redirect-after-post (str "/preview-job-post/" (:_id params))))
      (layout "This post has already been published."))
    (layout "Could not find job.")))

(defmethod post-preview-job [true "Edit Post"] [params]
  (redirect-after-post (str "/post-job/" (:_id params))))

(defmethod post-preview-job [true "Publish Post"] [params]
  (db/upsert-job! {:_id  (:_id params) :published (Date.)})
  (redirect-after-post "/"))

(defmethod post-preview-job :default [params]
  (layout "Bad http post."))

(defn get-preview-job [id]
  (let [params (db/get-job id)]
    (layout (job/content params :preview))))

(defn job [id]
  (let [params (db/get-job id)]
    (layout (job/content params))))

(defn home []
  (let [params (db/get-jobs :published)]
    (layout (home/content params) home/right-header)))


