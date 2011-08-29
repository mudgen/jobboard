(ns jobboard.web.layout
  (:use [hiccup.core :only (html)]
        [hiccup.page-helpers :only (doctype include-css include-js javascript-tag)]
        [jobboard.dev :only (dev-insert-firebug)]
        [jobboard.web.util :only (get-home with-home get-logo)]))


(defn layout
  ([content] (layout content ""))
  ([content, right-side-header]
     (html
      (doctype :html5)
      [:html
       [:head
        [:title "Programming and Software Jobs"]]
       (include-css (with-home "/css/styles.css"))
       ;;(dev-insert-firebug)
       [:body
        [:div#frame
         [:div#header
          [:div#logo
           [:a
            {:rel "Job Board"
             :title "Job Board"
             :href (get-home)}
            [:img
             {:style "border: 0;"
              :alt "Job Board"
;              :src (with-home "/images/blueparen-logo-small-smaller.png")
              :src (get-logo) 
              }]]]
          [:div#name-and-slogan
           [:div#site-name
            [:a
             {:rel "Home"
              :title "Home"
              :href (get-home)} "blueparen"]]
           [:div#section-slogan
            "Job board"]]
          [:div#right-side-header
           right-side-header]]
         [:div#content.curved-border
          content]]]])))

(def css [:body
          :margin "0px"
          :margin-bottom :50px
          :padding "0px"
          :background-color "rgb(248,248,248)"
          :font-family "Arial,FreeSans,sans-serif"
          [:div.curved-border
           :border "1px solid #e1e1e1"
           :-moz-border-radius "6px"
           :-webkit-border-radius "6px"
           :-khtml-border-radius "6px"
           :border-radius "6px"
           :-moz-box-shadow "3px 3px 3px #ccc" 
           :-webkit-box-shadow "3px 3px 3px #ccc"
           :box-shadow "3px 3px 3px #ccc"]
          [:span.block
           :display :inline-block]
          [:div#frame
           :margin "0 auto"
           :width "800px"
           [:div#header
            :border "0px solid black"
            :display "table"
            :padding "10px 0 5px 10px"
            [:div#logo
             :display "table-cell"]
            [:div#name-and-slogan
             :display "table-cell"
             :vertical-align "middle"
             :font-family "\"Trebuchet MS\",\"Myriad Pro\",\"Bitstream Vera Sans\", FreeSans, sans-serif"
             [:div#site-name
              :margin-left "-2px"
              :font-size "3.0em"
              :font-weight "300"
              [:a
               :text-decoration "none"
               :color "rgb(46,46,46)"
               :letter-spacing "-2px"]]
             [:div#section-slogan
              :color "rgb(226,84,0)"
              :font-size "1.6em"
              :font-weight "800"
              :text-transform "uppercase"
              :width "168px"
              :line-height "0.8em"
              :padding-bottom "10px"
              :letter-spacing "-1px"
              :text-align "right"]]
            [:div#right-side-header
             :display "table-cell"
             :vertical-align "middle"
             :width "100%"]]
           [:div#content
            :background-color "#FFFFFF"
            :padding "10px"]]])

