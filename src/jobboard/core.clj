(ns jobboard.core
  (:use [compojure.core :only (GET POST defroutes)]
        [jobboard.dev :only (dev)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [hiccup.middleware]
            [somnium.congomongo :as db]
            [jobboard.web.core :as web]
            [ring.util.serve]))

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
  (-> (handler/site main-routes)
      (hiccup.middleware/wrap-base-url)))

(defn start-server []
  (ring.util.serve/serve-headless app))
(defn stop-server []
  (ring.util.serve/stop-server))

;(start-server)






