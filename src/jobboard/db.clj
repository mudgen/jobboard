(ns jobboard.db
  (:require [somnium.congomongo :as db]))

(def *con* (db/make-connection :jobs))

(db/set-connection! *con*)

(defn close-connection []
  (db/close-connection *con*))

(defn object-id [id]
  (condp = (class id)
    org.bson.types.ObjectId id
    java.lang.String        (db/object-id id)
    nil))

(defn get-job [id]
  (try
    (db/fetch-by-id :job-posts (object-id id))
    (catch Exception e nil)))

(defn get-jobs
  ([] (get-jobs nil))
  ([where]
     (db/fetch :job-posts
               :where (if (= where :published)
                        {:published {:$exists true}})
               :limit 30
               :sort {:published -1})))

(defn upsert-job!
  ([params]
     (upsert-job! (get-job (:_id params)) params))
  ([job params]
     (if job
       (let [merged-job (merge job (dissoc params :_id))]
         (db/update! :job-posts job merged-job)
         merged-job)
       (db/insert! :job-posts params))))




