(ns diceware.core
  (:gen-class)
  (:require [clojure.java.io :as io]))

(def wordlist-url (java.net.URL. "http://world.std.com/~reinhold/diceware.wordlist.asc"))

(defn get-wordlist
  "Downloads the wordlist from the specified url (or wordlist-url) and filters only
  the lines with words."
  ([] (get-wordlist wordlist-url))
  ([url] (filter #(re-matches #"[1-6]{5}.*" %) (line-seq (io/reader url)))))

(defn split-line
  "Splits a wordlist line into it's key and value"
  [line]
  (if-let [[_ k v] (first (re-seq #"([1-6]{5}).(.*)" line))]
    [(Integer/parseInt k) v]))

(def wordlist-map (delay (reduce conj {} (map split-line (get-wordlist)))))

(defn roll-die []
  (inc (rand-int 6)))

(defn generate-key
  "Rolls a die 5 times to create a 5 digit number."
  []
  (let [exponents (map #(int (Math/pow 10 %)) (reverse (range 0 5)))]
    (reduce + (map #(* %1 %2) exponents (repeatedly roll-die)))))

(defn get-random-word
  "What it says on the tin"
  []
  (get @wordlist-map (generate-key)))

(defn generate-password
  "Gets n random words from the word list."
  [n] (repeatedly n get-random-word))

(defn -main
  ([] (-main 5))
  ([n] (let [n (if (integer? n) n
                   (try (Integer/parseInt n)
                        (catch Exception e 5)))]
         (println (generate-password n)))))
