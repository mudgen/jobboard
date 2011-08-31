(ns jobboard.web.post-job
  (:use [hiccup.form-helpers :only (text-field file-upload submit-button form-to text-area hidden-field)]
        [jobboard.web.util :only (s space with-base)]))

(def css
  [:div.post-job
   :font-size :13px
   [:h1.post-ad-header
    :margin "0px"
    :padding "0px"
    :padding-bottom "5px"
    :font-size "25px"]
   [:h4.post-section-header
    :color "rgb(119,145,37)"
    :padding "0px"
    :margin "0px 0px 10px 10px"
    :font-size "17px"]
   [:div.input-text
    :font-weight "bold"
    :margin-left "10px"
    [:input#job-title
     :width "300px"]
    [:input#job-location
     :width "300px"
     :margin-top "10px"]
    [:input#company-name
     :width "300px"]
    [:input#company-url
     :width "300px"]
    [:input#company-email
     :width "300px"]]
   [:div.examples
    :color "rgb(120,120,120)"
    :margin-left :86px]
   [:div.text-field
    :margin-top :10px
    :font-weight :bold
    :margin-left :10px
    [:textarea#job-description ]]])

(defn content [params]
  [:div.post-job
   [:div
    [:h1.post-ad-header
     "Step 1: Create your Ad"]
    [:h4.post-section-header
     "Tell us about the position"]
    (form-to
     [:post (with-base "/preview-job-post")]
     (when-let [id (:_id params)]
       (hidden-field "_id" (str id)))
     [:div.input-text
      [:span.block (s :width :75px)
       "Job Title: "]
      (text-field "job-title" (:job-title params))]
     [:div.examples
      "Examples: JVM Programmer or Lisp Specialist"]
     [:div.input-text
      [:span.block (s :width :75px)
       "Location: "]
      (text-field "job-location" (:job-location params))]
     [:div.examples
      "Examples: San Fransisco, Ca or Telecommute"]
     [:div.text-field
      "Job Description"
      [:div.curved-border (s :float :right :padding :5px :font-weight :normal :color "rgb(120,120,120)"
                             :background-color "rgb(238,238,248)")
       [:div (s :text-align :center :font-weight :bold)
        "Available HTML"]
       [:hr]
       [:ul (s :list-style-type "none" :padding-left :15px :padding-right :15px)
        [:li "&lt;b&gt;for bold&lt;/b&gt; <b>for bold</b>"]
        [:li "&lt;i&gt;for italics&lt;/i&gt; <i>for italics</i>"]
        [:li (s :margin-top :10px) "&lt;ul&gt;"
         [:div (s :margin-left :15px) "&lt;li&gt;for list item 1&lt;/li&gt;"]
         [:div (s :margin-left :15px) "&lt;li&gt;for list item 2&lt;/li&gt;"]
         "&lt;/ul&gt;"
         [:ul
          [:li "for list item 1"]
          [:li "for list item 2"]]]
        [:li (s :margin-top :10px) "&lt;ol&gt;"
         [:div (s :margin-left :15px) "&lt;li&gt;for list item 1&lt;/li&gt;"]
         [:div (s :margin-left :15px) "&lt;li&gt;for list item 2&lt;/li&gt;"]
         "&lt;/ol&gt;"
         [:ol
          [:li "for list item 1"]
          [:li "for list item 2"]]]]]
      (text-area {:rows :13 :cols :65} "job-description")]
     [:div.text-field
      "How do people apply for this job?"
      [:br]
      (text-area {:rows :3 :cols 60} "apply-for-job" (:apply-for-job params))]
     [:div.examples (s :margin-left :10px)
      "Examples: Send resume to hr@company.com or Apply for position at www.company.com"]
     [:br]
     [:hr]
     [:h4.post-section-header (s :margin-top :10px)
      "Tell us about your company"]
     [:div.input-text
      [:span.block (s :width :55px)
       "Name: "]
      (text-field "company-name" (:company-name params))]
     [:div.examples (s :margin-left :66px)
      "Enter company or organization name."]
     [:div.input-text (s :margin-top :10px)
      [:span.block (s :width :55px)
       "Logo: "]
      (file-upload "company-logo" )]
     [:div.examples (s :margin-left :66px)
      "Choose company logo image."]
     
     [:div.input-text (s :margin-top :10px)
      [:span.block (s :width :55px)
       "URL: "]
      (text-field "company-url" (:company-url params))]
     [:div.examples (s :margin-left :66px)
      "Example: http://www.google.com"]
     [:div.input-text (s :margin-top :10px)
      [:span.block (s :width :55px)
       "Email: "]
      (text-field "company-email" (:company-email params))]
     [:div.examples (s :margin-left :66px)
      "This is where we will send your confirmation email."]
     [:div (s :margin-left :64px :margin-top :20px :margin-bottom :10px)
      (submit-button "Step 2: Preview Post")])]])
