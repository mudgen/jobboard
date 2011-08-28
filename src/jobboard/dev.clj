(ns jobboard.dev
  (:use [hiccup.core :only (html)]))

(defn dev-insert-firebug []
  (html
   [:script
    {:type "text/javascript"
     :src "http://getfirebug.com/releases/lite/1.2/firebug-lite-compressed.js"
     :onload "window.onload=firebug.init;"}]))

