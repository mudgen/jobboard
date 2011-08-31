(ns jobboard.web.util
  (:use [gaka.core :only (inline-css)])
  (:import java.text.SimpleDateFormat
           java.util.Date))

(defn display-date [date]
  (let [formatter (SimpleDateFormat. "d MMMM")]
   (.format formatter date) ))

(defn with-base [& path]
  (let [base-path (get (System/getenv) "JOBBOARD_BASE_PATH" "")]
    (apply str base-path path)))  

(defn get-home [] 
  (with-base "/"))

(defn get-logo []
  (with-base "/images/blueparen-logo-small.png"))

(defn s [& css]
  {:style (apply inline-css css)})

(defn space [px]
  [:span.whitespace (s :padding-left px)]) 
