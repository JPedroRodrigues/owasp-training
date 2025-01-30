(ns clojure-owasp.owasp10)

(def db { :joao.rodrigues "joao.rodrigues", :guilherme.silveira "senha" })

;(defn login [username password]
;  (let [found-password (get db (keyword username))]
;    (= found-password password)))
;
;(println (login "joao.rodrigues" "pass"))
;(println (login "joao.rodrigues" "senha"))
;
;; there's no log here, which is a problem. Somebody can do a DDOS attack and won't be noticed
;(println (dotimes [_ 1000] (login "joao.rodrigues" "89jr43")))

; let's set a threshold here
(def username-attempts (atom {}))

(defn my-inc [x]
  (if x (inc x)
        1))

(def login-limit 30)
(defn attempt-login? [username]
  (swap! username-attempts update-in [username] my-inc)
  (<= (get @username-attempts username) login-limit))

(defn login [username password]
  (let [keyword-username (get db (keyword username))]
    (if (attempt-login? keyword-username)
      (let [found-password (get db keyword-username)]
        (if (= found-password password)
          (do (swap! username-attempts update-in [keyword-username] * 0)
            true)
          false))
      (throw (Exception. "Ha! Too many attempts!")))))

(println (dotimes [_ 29] (println (login "joao.rodrigues" "alksdjalskj"))))
(println @username-attempts)
(println (login "joao.rodrigues" "pass"))
(println @username-attempts)