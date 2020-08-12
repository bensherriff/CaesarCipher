# CaesarCipher
A simple Caesar Cipher. Use cases include encryption, decryption, and cracking encrypted messages. The reason for
creating this project was to try basic encryption and decoding using one of the oldest encryption methods.

## Introduction

### Encryption
Encrypt a string consisting of letters and whitespace using a key known as a Caesar codekey. Valid keys are integers 
between 0 and 26 for the letters within the English alphabet.

### Decryption
Decrypt a string consisting of letters and whitespace using a key. Valid keys are integers between 0 and 26.

### Cracking
Crack the encrypted string using multiple methods and compare results.
* Brute Force  
* Character Frequency  

Utilizing character table requires comparing encrypted character frequencies to normal character frequencies, 
paired with common words, bigrams, trigrams, quadgrams, and quintgrams.

## Data 

### Common Words<sup>[[1]](http://norvig.com/mayzner.html)</sup>


| Word | Percent | Word | Percent | Word | Percent | Word | Percent |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| THE | (7.14%) | BE | (0.65%) | BUT | (0.38%) | HAS | (0.22%) |
| OF | (4.16%) | BY | (0.63%) | HAVE | (0.37%) | THERE | (0.22%) |
| AND | (3.04%) | ON | (0.62%) | AN | (0.37%) | BEEN | (0.22%) |
| TO | (2.60%) | NOT | (0.61%) | HAD | (0.35%) | IF | (0.21%) |
| IN | (2.27%) | HE | (0.55%) | THEY | (0.33%) | MORE | (0.21%) |
| A | (2.06%) | I | (0.52%) | YOU | (0.31%) | WHEN | (0.20%) |
| IS | (1.13%) | THIS | (0.51%) | WERE | (0.31%) | WILL | (0.20%) |
| THAT | (1.08%) | ARE | (0.50%) | THEIR | (0.29%) | WOULD | (0.20%) |
| FOR | (0.88%) | OR | (0.49%) | ONE | (0.29%) | WHO | (0.20%) |
| IT | (0.77%) | HIS | (0.49%) | ALL | (0.28%) | SO | (0.19%) |
| AS | (0.77%) | FROM | (0.47%) | WE | (0.28%) | NO | (0.19%) |
| WAS | (0.74%) | AT | (0.46%) | CAN | (0.22%) |
| WITH | (0.70%) | WHICH | (0.42%) | HER | (0.22%) |

### Monogram Frequencies<sup>[[1]](http://norvig.com/mayzner.html)</sup><sup>[[2]](https://en.wikipedia.org/wiki/Letter_frequency)</sup>

| Monogram | Percent | Monogram | Percent |
| :---: | :---: | :---: | :---: |
| E | (12.702%) | M | (2.406%)
| T | (9.356%) | F | (2.228%)
| A | (8.167%) | C | (2.202%)
| O | (7.507%) | G | (2.015%)
| I | (6.966%) | Y | (1.994%)
| N | (6.749%) | P | (1.929%)
| S | (6.237%) | B | (1.492%)
| H | (6.094%) | K | (1.292%)
| R | (5.987%) | V | (0.978%)
| D | (4.253%) | J | (0.153%)
| L | (4.025%) | X | (0.150%)
| U | (2.758%) | Q | (0.095%)
| W | (2.560%) | Z | (0.077%)


### Bigram Frequencies<sup>[[1]](http://norvig.com/mayzner.html)</sup>

| Bigram | Percent | Bigram | Percent | Bigram | Percent | Bigram | Percent |
| :---: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| TH | (3.56%) | TE | (1.20%) | AS | (0.87%) | EA | (0.69%) |
| HE | (3.07%) | OF | (1.17%) | OU | (0.87%) | RA | (0.69%) |
| IN | (2.43%) | ED | (1.17%) | IO | (0.83%) | CE | (0.65%) |
| ER | (2.05%) | IS | (1.13%) | LE | (0.83%) | LI | (0.62%) |
| AN | (1.99%) | IT | (1.12%) | VE | (0.83%) | CH | (0.60%) |
| RE | (1.85%) | AL | (1.09%) | CO | (0.79%) | LL | (0.58%) |
| ON | (1.76%) | AR | (1.07%) | ME | (0.79%) | BE | (0.58%) |
| AT | (1.49%) | ST | (1.05%) | DE | (0.76%) | MA | (0.57%) |
| EN | (1.45%) | TO | (1.04%) | HI | (0.76%) | SI | (0.55%) |
| ND | (1.35%) | NT | (1.04%) | RI | (0.73%) | OM | (0.55%) |
| TI | (1.34%) | NG | (0.95%) | RO | (0.73%) | UR | (0.54%) |
| ES | (1.34%) | SE | (0.93%) | IC | (0.70%) |
| OR | (1.28%) | HA | (0.93%) | NE | (0.69%) |

### Trigram Frequencies
TODO

### Quadgram Frequencies
TODO

### Quintgram Frequencies
TODO

## How to use
TODO

## History
* **v0.2.0** GUI and Encryption update  
  Redesigned initial gui and implemented correct encryption/decryption
* **v0.1.0** Init  
  Initial commit and code
