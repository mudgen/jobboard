(ns jobboard.web.job
  (:use [hiccup.form-helpers :only (text-field file-upload submit-button form-to hidden-field)]
        [hiccup.page-helpers :only (link-to)]
        [jobboard.web.util :only (s space with-home)]))

(def css
  [:div#job-ad
   :font-size :16px
   :margin-left :10px
   [:h1#preview-ad
    :margin "0px"
    :padding "0px"
    :padding-bottom "5px"
    :font-size "25px"]
   [:h1#job-ad-header
    :margin "0px"
    :padding "0px"
    :padding-bottom "5px"
    :font-size "28px"]
   [:h2#job-company-name
    :font-size :18px
    :margin :0px]
   [:div#job-location
    :font-size :18px
    :margin-top :5px]
   [:div#apply-for-job
    :margin-top :10px
    :padding :10px
    :background-color "rgb(240,240,250)"]
   [:span#edit-or-continue
    :margin-left :200px]])

(defn content
  ([params] (content params nil))
  ([params preview?]
     [:div#job-ad
      (str params)
      (if preview?
        [:h1#preview-ad
         "Step 2: Preview your ad"
         [:span.block {:id "edit-or-continue"}
          (form-to [:post (with-home "preview-job-post")]
                   (hidden-field "_id" (:_id params))
                   [:input {:type "submit" :name "action" :value "Edit Post"}] " | "
                   [:input {:type "submit" :name "action" :value "Publish Post"}])]])
      (if preview? [:hr])
      [:h1#job-ad-header
       (:job-title params)]
      [:h2#job-company-name
       (:company-name params)]
      [:div#job-location
       "Location: " (:job-location params)]
      [:div.job-link
                                        ;    (apply link-to (repeat 2 (:company-url params)))
       ]
      [:hr]
      [:div#job-description
       (:job-description params)]
      [:div#apply-for-job {:class :curved-border}
       [:div (s :font-weight :bold :margin-bottom :10px)
        "Apply for this position"]
       (:apply-for-job params)]
      ]))
