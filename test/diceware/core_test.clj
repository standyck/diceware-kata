(ns diceware.core-test
  (:require [clojure.test :refer :all]
            [diceware.core :refer :all]))

(deftest count-diceware-words
  (testing "Should be 7776 words in the wordlist"
    (is (= 7776 (count @wordlist-map)))))

(deftest evaluate-wordlist
  (testing "All wordlist keys are integers"
    (is (every? integer? (keys @wordlist-map))))
  (testing "All wordlist vals are strings"
    (is (every? string? (vals @wordlist-map))))
  (testing "No duplicate words in wordlist"
    (is (= 7776 (count (set (vals @wordlist-map)))))))
