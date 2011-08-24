(ns jobboard.web.util
  (:use [jobboard.dev :only (dev)]
        [gaka.core :only (inline-css)]
        [hiccup.core :only (resolve-uri)])
  (:import java.text.SimpleDateFormat
           java.util.Date))

(defn display-date [date]
  (let [formatter (SimpleDateFormat. "d MMMM")]
   (.format formatter date) ))

(defn with-home [path]
  (if dev
    (str "http://localhost:3000/" path)
    (str "/" path)))  

(defn get-home [] 
  (with-home ""))

(defn get-logo []
  (if dev
    "/public/corolla_log.png"
    "/sites/default/files/corolla_logo.png"))

(defn s [& css]
  {:style (apply inline-css css)})

(defn space [px]
  [:span.whitespace (s :padding-left px)]) 
