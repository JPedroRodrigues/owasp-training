(ns clojure-owasp.owasp8)

(defn- treat-dot-commas [word]
  (-> word
      (clojure.string/replace "." "")
      (clojure.string/replace "," ".")
      clojure.edn/read-string))

(println (treat-dot-commas "11111.111111,30"))
(println (treat-dot-commas "#= (+ 2 2)"))