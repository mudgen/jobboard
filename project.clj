(defproject jobboard "1.0.0-SNAPSHOT"
  :description "A Compojure jobboard application"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [ring/ring-jetty-adapter "0.3.11"]
                 [compojure "0.6.5"]
                 [congomongo "0.1.7-SNAPSHOT"]
                 [hiccup "0.3.6"]
                 [gaka "0.2.0"]]
  :ring {:handler jobboard.core/app
         :destroy jobboard.db/close-connection}
  :main jobboard.core)
