(ns jobboard.dev
  (:use [hiccup.core :only (html)]))
(def dev true)
(defn dev-insert-firebug []
     (if dev
       (html
        [:script
         {:type "text/javascript"
          :src "http://getfirebug.com/releases/lite/1.2/firebug-lite-compressed.js"
          :onload "window.onload=firebug.init;"}])
       ""))

