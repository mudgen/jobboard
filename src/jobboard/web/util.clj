(ns jobboard.web.util
  (:use [gaka.core :only (inline-css)])
  (:import java.text.SimpleDateFormat
           java.util.Date))

(defn display-date [date]
  (let [formatter (SimpleDateFormat. "d MMMM")]
   (.format formatter date) ))

(defn with-home [path]
  (str "/" path))  

(defn get-home [] 
  (with-home ""))

(defn get-logo []
  (with-home "images/blueparen-logo-small.png"))

(defn s [& css]
  {:style (apply inline-css css)})

(defn space [px]
  [:span.whitespace (s :padding-left px)]) 
