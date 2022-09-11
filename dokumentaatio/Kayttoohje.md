# Käyttöohje

### Alkuhuomio:

Koitin rakentaa projektista Jar tiedoston ja sainkin sen tehtyä niin, että se toimii omalla koneellani, jolla olen kehittänyt ohjelmaa.
Kuitenkin kun koitin toisella koneella ajaa Jar:in, se ei toiminut. Tämä johtunee siitä, että toisella koneella ei ole käyttöliittymän
käyttämiä kuvatiedostoja. Yritin kopioida kuvatiedostot samaan kansioon Jar tiedoston kanssa, mutta tämä ei auttanut.  
Ongelma lienee osittain siinä, että ohjelma käyttää absoluuttisia tiedostopolkuja käyttöliittymän käyttämiin kuviin. Yritin pitkään
saada suhteelliset tiedostopolut toimimaan, mutta siitä aiheutui "java.lang.IllegalArgumentException: Invalid URL or resource not found".
Täten ainoa keino miten itse saan tällä hetkellä ohjelman toimimaan, on ajaa se suoraan projektikansiosta tai NetBeansistä.

### Asentaminen:

- Kloonaa projekti komennolla `git clone git@github.com:KA0Sgames/TiraHarkka-Go-game.git`
- Korvaa käyttöliittymän kuvatiedostojen absoluuttiset polut suhteellisilla poluilla `src/main/kuvat/[kuvan nimi].png` tai
absoluuttisilla poluilla siinä koneessa jolla ohjelmaa käytetään.  
- Korvattavat rivit ovat `GoPeli/Source Packages[java]/GoPeli.Kayttoliittyma` paketissa, luokassa `Kayttoliittyma.java`
- Rivi 107, uuden Image-olion luonnissa parametrina oleva String, kuvan Board.png polku.
- Rivi 221, samoin kuvan Black_stone.png polku.
- Rivi 233, samoin kuvan White_stone.png polku.
- Rivi 269, samoin kuvan LastMoveMarker.png polku.
- Tarvittavat kuvat löytyvät kansiosta `GoPeli/src/main/kuvat`


### Pelaaminen:

- Projektin voi ajaa NetBeansissä projektin asentamisen ja kuvien polkujen korjaamisen jälkeen Run-valikosta Run Project (GoPeli)
vaihtoehdolla.
- Vaihtoehtoisesti komentorivillä mene projektin ohjelman pääkansioon `/TiraHarkka-Go-game/GoPeli` ja aja peli komennolla
`gradle run`

- Pelissä käyttäjä pelaa mustilla ja tekoäly valkeilla. Go:ssa musta pelaaja aloittaa, joten käyttäjällä on ensimmäinen vuoro.
- Kivi pelataan haluttuun risteyspisteeseen laudalla klikkaamalla kyseistä risteyspistettä hiiren vasemmalla napilla.
- Laudan oikealla puolella valikossa on vaihtoehtoiset napit vuoron passaamiselle ja pelin luovuttamiselle.
- Huom. tässä toteutuksessa oman siirron lisäämisen jälkeen täytyy klikata lautaa uudestaan hiirellä, jotta tekoäly alkaa miettimään
omaa siirtoaan. Tämä siitä syystä, että oma siirto näkyy laudalla heti sen valinnan jälkeen.
- Tekoäly miettii omia siirtojaan noin 10 sekuntia, mikäli haluaa muuttaa tekoälyn käyttämää aikaa, sen voi tehdä muokkaamalla
`GoPeli/Source Packages[java]/GoPeli.tekoaly` pakkauksen alla olevaa luokkaa `Tekoaly.java` riviä 66 muuttamalla riviksi
`long loppu = alku + haluttu sekuntimäärä * 1000;`
- Tekoäly luovuttaa, jos parhaan siirron simuloitujen pelien voittoprosentti on alle 10%.
- Tällä hetkellä ohjelmassa on bugi, joka aiheuttaa sen, että jos tekoälyn pitäisi passata, niin aiheutuu NullPointerException.

### Testaaminen:

- Testit voi ajaa komennolla `gradle test`
- Testien tuloksia voi tarkastella tiedostosta `/GoPeli/build/reports/tests/test/index.html`
- Testit ajamalla generoituu testikattavuusraportti, jota voi tutkia tiedostosta `/GoPeli/build/reports/jacoco/test/html/index.html`

### Javadoc:

- Javadoc voidaan generoida komennolla `gradle javadoc`
- Javadoc:ia voidaan tarkastella tiedostosta `/GoPeli/build/docs/javadoc/index.html`