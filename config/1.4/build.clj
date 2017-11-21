(require '[autodoc.build-html :as h] 
         '[autodoc.params :as p]
         '[clojure.edn :as e]) 
(import [java.io PushbackReader FileReader File]) 

(defn edn-read [f]
  (let [EOF (Object.)]
    (with-open [rdr (PushbackReader. (FileReader. (File. f)))]
      (e/read {:eof EOF} rdr))))

(def shared (edn-read "../params.clj"))

(p/merge-params
  (merge shared    
    {:root (.getAbsolutePath (File. "../../repo/"))
     :template-dir "old-templates"}))

(h/make-all-pages 
  {:name "clojure-1.4.0" :version "v1.4" :status "legacy"}
  (:branches shared)
  (edn-read "analysis.edn"))