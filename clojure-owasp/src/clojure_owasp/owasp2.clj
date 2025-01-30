(ns clojure-owasp.owasp2
  (:require [crypto.password.bcrypt :as password]))

(def database (atom {}))
(defn add [table document]
  (swap! database update-in [table] conj document))

; text plain version with a bunch of risks
;(defn register-new-user! [username password]
;  (add :users { :username username, :password password }))

(def encrypted-password (password/encrypt "pepino"))
(println encrypted-password)

(defn read-file [filename]
  (-> filename
      slurp
      clojure.string/split-lines))

(def common-passwords (read-file "src/common-passwords.txt"))
(println common-passwords)

(defn is-common? [password]
  (some #(= password %) common-passwords))

(println (password/check "pepino" encrypted-password))

(defn register-new-user! [username password]
  (if (is-common? password)
    (throw (Exception. "Senha muito simples"))
    (let [encrypted (password/encrypt password)]
      (add :users { :username username, :password encrypted }))))


(println (register-new-user! "joao.grr" "pepino"))

(println
  (register-new-user! "pedro.joao" "solidão"))

(println (is-common? "solidão") (is-common? "oiasud982q37hfahuhoqewur"))
