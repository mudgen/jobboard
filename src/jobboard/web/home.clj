(ns jobboard.web.home
  (:use [jobboard.web.util :only (with-home display-date)]
        [hiccup.page-helpers :only (link-to)]))

(def content-css
  '([:h1#job-header
     :margin "0px"
     :color "rgb(119,145,37)"
     :font-weight "600"
     :font-size "20px"]
    [:ul#job-list
     :padding "0px"
     [:li
      :list-style-type "none"
      :font-size "14px"
      [:span.location
       :font-weight "bold"
       :padding-right "15px"]
      [:span.job-title
       :padding-right "15px"
       :margin-top :5px
       [:a
        :text-decoration :none
        :color :#086782]
       [:a:hover
        :text-decoration :underline
        :color "rgb(226,84,0)"]]
      [:span.at
       :color "rgb(100,100,100)"
       :padding-right "8px"]
      [:span.company
       ]
      [:span.date
       :float "right"]]]))

(defn content [params]
  [:div.home
   [:h1#job-header
    "Jobs"]
   (map (fn [job]
            [:ul#job-list
             [:li
              [:span.location (:job-location job)]
              [:span.job-title (link-to (with-home (str "job/" (:_id job))) (:job-title job))]
              [:span.at "at"]
              [:span.company (:company-name job)]
              [:span.date (display-date (:published job))]]]) params)])

(def right-header-css
  [:div#post-job
   :float "right"
   :padding "10px"
   :padding-top "6px"
   :background-color "rgb(119,145,37)"
   :-moz-border-radius "6px"
   :-webkit-border-radius "6px"
   :-khtml-border-radius "6px"
   :border-radius "6px"
   :text-align "center"
   :vertical-align "center"
   [:a
    :color "rgb(255,255,255)"
    :font-weight "800"
    :font-size "15.2px"]])

(def right-header
  [:div.home
   [:div#post-job
    [:a
     {:rel "Post a Job"
      :title "Post a Job"
      :href (with-home "post-job")}
     "Post a job"]]])

(def css
  [:div.home
   content-css
   right-header-css])

