# Viikkoraportti 4

### Mitä olen tehnyt tällä viikolla?

- 8.8. Luin miten pelilaudan tilanne ja erityisesti miten pelitilanne (sisältäen lautatilanteen lisäksi kaapatut vangit, edelliset lautatilanteet yms. on toteutettu Deep Learning and the game of Go-kirjassa. 1 tunti.
- 8.8. Yksinkertaistin Koordinaatti-luokan ja käytän null arvoa kun tietyssä lautapisteessä ei ole kiveä. Kirjoitin JUnit testit Koordinaatti ja Ryhma luokille. Yhteensä 4 tuntia.
- 9.8. Kirjoitin pelilautaa kuvaavan luokan ja muokkasin PelinHallinnan siirron lisäämisen toimimaan Pelilauta luokassa muokatuilla Koordinaatti ja Ryhma luokilla. Kirjoitin myös jonkinverran testejä Pelilauta luokalle. Yhteensä 3 tuntia.

###### Käytetty aika tällä viikolla yhteensä n. 8 tuntia.

### Miten ohjelma on edistynyt?

- 8.8. Koordinaatti ja Ryhma luokat (luultavasti) lopullisessa muodossaan ja niiden testit, sekä Koordinaatti luokan Javadoc dokumentaatio kirjoitettu.
- 9.8. Pelilauta luokka kirjoitettu ja sille testejä, tosin täyttä testikattavuutta en vielä saanut.

### Mitä opin tällä viikolla?

- 8.8. Null arvoja voi käyttää hyväksi, kuvaamaan tiettyä arvoa, jos tarvitsee yksinkertaisia arvoja.

### Mikä jäi epäselväksi tai on tuottanut vaikeuksia?

### Mitä teen seuraavaksi?

- 8.8. Kirjoitan luokan pelilaudan tilannetta kuvaamaan, käyttäen apuna PelinHallintointi luokkaa, erityisesti siirron lisäämis metodia, jota joutuu mahdollisesti hieman muokkaamaan Koordinaatin muutosten vuoksi. Lisäksi testit ja dokumentaatio ko. luokalle sekä dokumentaatio Ryhma luokalle.
- 9.8. JUnit testejä Pelilauta luokalle. Pitää myös selvitellä miksi Koordinaatti ja Ryhma luokilla ei ole ihan täyttä testikattavuutta. Pelistatus luokan kirjoitus, joka tavallaan korvaa pelinhallinnan, sisältäen laillisten siirtojen tarkastuksen yms.