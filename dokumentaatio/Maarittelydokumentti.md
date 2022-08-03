### Go peli tekoälyä vastaan

- Käytettävänä kielenä Java.
- Muita ohjelmointikieliä en osaa siinä määrin, että pystyisin vertaisarvioimaan niillä tehtyjä töitä.

- Tekoäly käyttää Monte Carlo puuhakua, jota ajetaan halutun ajan jokaisella tekoälyn siirrolla
- Pelin olen ajatellut käyttävän syvyys- tai leveyshakua tarkistamaan tuleeko kiviä syödyksi siirron seurauksena (tätä käytetään mm. tarkistamaan onko yritetty siirto laillinen, sekä siirron jälkeen päivittämään lautatilanne ja vangitut kivet). Näiden aikavaativuus on O(n+m) jossa n on solmujen lukumäärä ja m kaarten lukumäärä, tässä kiviryhmän koko vaikuttaa siis käytettävään aikaan, sekä jos kivillä on vapauksia (laskenta keskeytetään jos kivillä on vapauksia, koska silloin ne eivät tule syödyksi).
- Pelitilanne tallennetaan olioon, jossa on lautatilanne kokonaislukutaulukkona ja lisäksi pelitilanne parin edeltävän siirron ajalta samoin, sekä kokonaislukuina syötyjen kivien määrät, sekä boolean muuttujana tieto kumman pelaajan vuoro seuraavalla siirrolla

- Ongelmana on saada aikaan tekoäly joka pystyy pelaamaan mahdollisimman hyvin. Lautakoon huomioon ottaen, tavoitteena on pelitaso, jota en pysty itse voittamaan. Ongelma on mielenkiintoinen sillä pelaan itse Go:ta ja nykyään tekoälyä käytetään paljon pelin opiskelussa, joten historiallisesti modernien tekoälyjen edeltäjät ovat kiinnostavia.
- Monte Carlo puuhaku on ymmärtääkseni suuressa roolissa vielä nykyisissäkin tekoälyissä ja se on ollut suuri harppaus eteenpäin ennen neuroverkkojen käyttöä kun mittarina käytetään Go tekoälyjen vahvuutta.

- Ohjelma saa syötteiksi ihmisen (pelaajan) tekemät siirrot, nämä muuttavat lautatilannetta, mikäli ne ovat laillisia siirtoja, mutta toisaalta täytyy myös tarkistaa, että yritetty siirto on laillinen.

- Vangittujen kivien ja laillisten siirtojen tarkistus O(n+m) ajassa.
- Monte Carlo puuhakua ajetaan vakio sekuntimäärä.
- Tilavaativuuksista en vielä ole varma, mutta olettaisin tilavaativuus laillisten siirtojen tarkastuksessa ja kivien syönnissä on O(n) sillä niissä käytetään listaa, johon tallennetaan jo käydyt solmut.
- Monte Carlo puuhaun tilavaativuus ei liene tiedossa? Tämä johtunee siitä, että haun edetessä puu laajenee, mutta tämä riippuu ajasta, kuinka kauan algoritmin annetaan suorittaa, sekä mahdollisesti laitteistosta.

Lähteet:
- https://www.cs.helsinki.fi/u/ahslaaks/kkkk.pdf
- https://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.699.1458&rep=rep1&type=pdf
- Pumperla, M., Ferguson, K., 2019, Deep Learning and the Game of Go, Manning Publications

- Kuulun tietojenkäsittelytieteen kandiohjelmaan
- Dokumentaation ja koodiprojektin kieli: Suomi