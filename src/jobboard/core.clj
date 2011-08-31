(ns jobboard.core
  (:use [compojure.core :only (GET POST defroutes)]
        [ring.adapter.jetty :only (run-jetty)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [hiccup.middleware]
            [somnium.congomongo :as db]
            [jobboard.web.core :as web]))

(defroutes main-routes
  (GET "/" [] (web/home))
  (GET "/test" [:as request] (str request))
  (GET "/job/:id" [id] (web/job id))
  (GET "/post-job" [] (web/post-job))
  (GET "/post-job/:id" [id] (web/post-job id))
  (POST "/preview-job-post" [& params] (web/post-preview-job params))
  (GET "/preview-job-post/:id" [id] (web/get-preview-job id))
  (GET "/css/styles.css" [] (web/get-css "css/styles.css"))
  (route/resources "/")
  (route/files "/js" {:root "javascript"})
  (route/not-found "Page not found"))

(def app
  (-> main-routes
     (handler/site) 
     (hiccup.middleware/wrap-base-url)))

(defonce server nil)

(defn start-server []
  (if server
    (.start server)
    (def server
      (run-jetty (var app) {:join? false :port 8080}))))

(defn stop-server []
  (.stop server))

(start-server)










