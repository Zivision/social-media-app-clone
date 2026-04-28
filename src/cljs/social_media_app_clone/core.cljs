(ns social-media-app-clone.core
  (:require [reagent.core :as r]
            [reagent.dom.client :as rdomc]
            [ajax.core :refer [GET]]))

(defn greeting [message]
  [:h1 message])

(defn app []
  [:div
   [greeting "Hello World! This is the Social Media App Clone"]])

;; Delay create-root call until app is mounted,
;; this is only because this ns is already part of the demosite build.
;; Delay is not usually needed for regular apps.
(defonce root (delay (rdomc/create-root (.getElementById js/document "app"))))

(defn ^:export init []
  (rdomc/render @root [app]))
