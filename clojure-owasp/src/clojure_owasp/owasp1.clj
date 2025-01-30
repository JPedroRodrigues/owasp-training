(ns clojure-owasp.owasp1)

(use '[clojure.java.shell :only [sh]])

(defn run-cluster [config-file]
  (let [command (str "/bin/kafka " config-file)]
    (println command)
    ; problematic
    ; (sh "bash" "-c" command)
    ; fixed
    ; (sh "/bin/kafka" config-file)
  )
)

(run-cluster "server.properties")
(run-cluster "server.properties; touch hacked.txt")

; outro exemplo
(defn login [username password]
  (let [sql (str "select * from User where username='" username "' and password='" password "'")]
    (println sql)
    ; sql run script
    ))

(login "joao" "senha")
(login "joao" "' or id=1 having '1'='1")