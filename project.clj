(defproject jobboard "1.0.0-SNAPSHOT"
  :description "A Compojure 'Hello World' application"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]
                 [compojure "0.6.5"]
                 [congomongo "0.1.7-SNAPSHOT"]
                 [hiccup "0.3.6"]
                 [gaka "0.2.0"]
                 [ring-serve "0.1.1"]]
  :dev-dependencies [[lein-ring "0.4.5"]]
  :ring {:handler jobboard.core/app
         :destroy jobboard.db/close-connection}
  :main jobboard.core)
