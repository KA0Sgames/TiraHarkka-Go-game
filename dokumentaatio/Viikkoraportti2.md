# Viikkoraportti 2

### Mitä olen tehnyt tällä viikolla?

- 25.7. Projektin muutos VS Codesta NetBeansin puolelle ja Gradlen muutos pois Kotlinista. JavaFx säätäminen toimimaan. Yhteensä noin 6 tuntia.
- 27.7. Katsonut Java tutoriaaleja YouTubesta ja koittanut saada pelilaudan piirrettyä käyttöliittymällä. Noin 3 tuntia käytetty.
- 28.7. Tapellut suhteellisen polun kanssa, onnistumatta saamaan sitä toimimaan. Kirjoitin hieman lisää käyttöliittymää ja aloitin ohjelmalogiikan tekemisen luomalla ja kirjoittamalla hieman toiminnallisuutta pelin hallinnointiin tarkoitetulla luokalla, sekä koordinaatti luokan lisäys. Yhteensä reilu 4 tuntia, mutta vaikea arvioida kun pätkittäistä työtä.
- 29.7. Javadoc säädetty toimimaan. Noin 2 tuntia.
- 29.7. Käyttöliittymään lisätty sivupaneeli. Luokat tehty tekoälylle ja siirron laillisuuden tarkastukselle. Yhteensä noin 2 tuntia.
- 30.7. JUnit testit, sekä Jacoco säädetty toimimaan ja configuroitu testikattavuutta varten, itse testejä ei vielä kirjoitettu kuin muutama, jotta JUnit ja Jacoco asetukset sai tarkastettua. Configuroinnissa Jacocon testikattavuusraportista jätin käyttöliittymän pois, kuten muistaakseni tehtiin Ohjelmistotekniikka kurssilla. Noin 2 tuntia.
- 30.7. JUnit testien kirjoitusta PelinHallinta-luokalle, en saanut vielä ihan täyttä testikattavuutta, mutta 84% kyseiselle luokalle. Noin 1,5 tuntia.

###### Käytetty aika tällä viikolla yhteensä noin 20,5 tuntia.

### Miten ohjelma on edistynyt?

- 25.7. Graafinen käyttöliittymä saatu näyttämään jotain JavaFx avulla.
- 27.7. Saan pelilaudan piirrettyä ruudulle käyttäen absoluuttista polkua kuvaan, mutta en suhteellisella polulla.
- 28.7. Hieman lisää käyttöliittymää. Ohjelmalogiikkaa aloitettu, PelinHallinnointi-luokka + Koordinaatti-luokka luotu.
- 29.7. Javadoc säädetty toimimaan. Käyttöliittymään lisätty sivupaneeli jossa näkyy pelin aikana syötyjen kivien määrä.
- 30.7. JUnit testit otettu käyttöön (ei vielä kirjoitettu itse testejä) ja Jacoco konfiguroitu. JUnit testejä kirjoitettu PelinHallinta-luokalle.

### Mitä opin tällä viikolla?

- 27.7. Valmiin kuvan piirtämistä JavaFx:llä.
- 30.7. Gradle projektin konfigurointia, sillä Ohjelmistotekniikka kurssilla käytin Mavenia.

### Mikä jäi epäselväksi tai on tuottanut vaikeuksia?

- 25.7. JavaFx kirjaston käyttöönotto oli vaikeaa, kun netissä olevat ohjeet tuntuivat olevan ristiriitaisia.
- 27.7. Miten Windowsia käyttäen saa käytettyä kuvaa suhteellisella polulla tiedostoon.
- 28.7. Edelleen suhteellisen polun kanssa ongelmia vaikka Hannun kanssa juteltiin tästä ja koitin saada toimimaan neuvojen avulla. Useamman samanvärisen kiven piirto laudalle yhtäaikaa - tähän pitänee tehdä lista ImageView-olioita joilla kaikilla omat koordinaattinsa.
- 29.7. Ohjelman rakenne on vielä epäselvä. En tiedä kuinka järkevä pelin hallinnointiin käytettävä luokka on, tein siirron laillisuuden tarkastamiselle oman luokan, jotta tekoäly voi käyttää sitä lisätessä uusia siirtoja hakupuuhun.
- 30.7. Konfiguroin Jacocon jättämään käyttöliittymän testikattavuuden ulkopuolelle, sillä näin muistaakseni tehtiin OhTe-kurssilla, pitäisikö käyttöliittymän olla mukana testikattavuudessa ja sille kirjoittaa testejä?

### Mitä teen seuraavaksi?

- 25.7. Alan kirjoittamaan käyttöliittymää, jolla saan pelilaudan näytettyä.
- 27.7. Kirjoitan luokan pelin hallinnointiin ohjelmalogiikan puolelle, jolta voi kysyä lautatilannetta. Myös pelilaudan ja kivien kuvat pitää skaalata puoleen nykyisestä, jotta ne mahtuvat hyvin kaikille näytöille.
- 28.7. Koitan saada useamman samanvärisen kiven näyttämisen yhtäaikaa toimimaan. Lisää ohjelmalogiikkaa, sallitun siirron tarkastusta.
- 29.7. Testejä pitäisi aloittaa kirjoittamaan ja konfiguroida projekti niin, että testit toimivat ja niistä voi generoida kattavuusraportin.
- 30.7. Javadoc kommenttien kirjoitus, sekä Checkstylen käyttöönotto. JUnit testien kirjoitusta, jotta saa täyden testikattavuuden.