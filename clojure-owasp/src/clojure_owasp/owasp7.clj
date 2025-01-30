(ns clojure-owasp.owasp7
  (:require [crypto.password.bcrypt :as password]))

(def database (atom {}))
(defn add [username document]
  (swap! database assoc-in [username] document))

(def encrypted-password (password/encrypt "pepino"))

(defn read-file [filename]
  (-> filename
      slurp
      clojure.string/split-lines))

(def common-passwords (read-file "src/common-passwords.txt"))
(println common-passwords)

(defn is-common? [password]
  (some #(= password %) common-passwords))

(println (password/check "pepino" encrypted-password))

(defn register-new-user! [username password name]
  (if (is-common? password)
    (throw (Exception. "Senha muito simples"))
    (let [encrypted (password/encrypt password)]
      (add username { :username username, :password encrypted, :name name }))))

(defn load-user [username]
  (get @database username))

(println (register-new-user! "joao.grr" "pepino" "<script>alert('oi');</script>"))
(println (register-new-user! "pedro.joao" "pamonha" "Pedro João"))

(def public-profile-template "<html>
                                <head><title>Welcome</title></head>
                                <body>
                                  <h1>{{NAME}}</h1>
                                  {{USERNAME}}
                                  </body>
                                </body>
                             </html>")

(defn replace-symbol [content [symbol-name symbol-value]]
  (let [key (str "{{" symbol-name "}}")]
    (clojure.string/replace content key symbol-value)))

(defn render-template [content symbols]
  (reduce replace-symbol content symbols))

(defn view-public-profile [username]
  (let [user (load-user username)]
    (println username user)
    (render-template public-profile-template { "USERNAME" (:username user)
                                              "NAME" (:name user) })))

(println @database)
(println (view-public-profile "joao.grr"))
