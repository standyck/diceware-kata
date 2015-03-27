(ns diceware.core
  (:gen-class)
  (:require [clojure.java.io :as io]))

(def wordlist-url (java.net.URL. "http://world.std.com/~reinhold/diceware.wordlist.asc"))

(defn get-wordlist
  ([] (get-wordlist wordlist-url))
  ([url] (filter #(re-matches #"[1-6]{5}.*" %) (line-seq (io/reader url)))))

(defn split-line [line]
  (let [splitted (first (re-seq #"([1-6]{5}).(.*)" line))]
    [(Integer/parseInt (second splitted)) (last splitted)]))

(def wordlist-map (delay (reduce conj {} (map split-line (get-wordlist)))))

(defn roll-die []
  (inc (rand-int 6)))

(defn generate-key []
  (let [exponents (map #(int (Math/pow 10 %)) (reverse (range 0 5)))]
    (reduce + (map #(* %1 %2) exponents (repeatedly roll-die)))))

(defn get-random-word []
  (get @wordlist-map (generate-key)))

(defn genpw [n] (repeatedly n get-random-word))

(defn -main
  ([] (-main 5))
  ([n] (let [n (if (integer? n) n
                   (try (Integer/parseInt n)
                        (catch Exception e 5)))]
         (println (genpw n)))))
