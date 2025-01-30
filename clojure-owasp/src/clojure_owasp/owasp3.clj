(ns clojure-owasp.owasp3
  (:require [crypto.password.bcrypt :as password]))

(def database (atom {}))
(defn add [table document]
  (swap! database update-in [table] conj document))

(def encrypted-password (password/encrypt "pepino"))

(defn read-file [filename]
  (-> filename
      slurp
      clojure.string/split-lines))

(def common-passwords (read-file "src/common-passwords.txt"))

(defn is-common? [password]
  (some #(= password %) common-passwords))

(defn register-new-user! [username password]
  (if (is-common? password)
    (throw (Exception. "Senha muito simples"))
    (let [encrypted (password/encrypt password)]
      (add :users { :username username, :password encrypted }))))

(println common-passwords)
(println encrypted-password)
(println (password/check "pepino" encrypted-password))
(println (register-new-user! "joao.grr" "pepino"))
(println (register-new-user! "pedro.joao" "sangria"))
(println (is-common? "solidão") (is-common? "oiasud982q37hfahuhoqewur"))


(defn continue [chain path parameters]
  (if chain
    (let [next-one (first chain)]
      (next-one (rest chain) path parameters))))

(defn execution-layer [chain path parameters]
  (println "Executing for path"))

(defn do-upload [parameters]
  (println parameters))

(defn upload-layer [chain path parameters]
  (if (:uploaded-file parameters)
    (do-upload parameters))
  (continue chain path parameters))

(defn log-layer [chain path parameters]
  ; dangerous section. Parameters should not be printed that easily
  (println path parameters)
  (continue chain path parameters))

(defn continue [chain path parameters]
  (if chain
    (let [next-one (first chain)]
      (next-one (rest chain) path parameters))))

(defn service [path parameters]
  (let [chain [log-layer upload-layer execution-layer]]
    (continue chain path parameters)))

(service "/upload" {:uploaded-file "hi.txt"})
(service "/login" {:password "password"})
