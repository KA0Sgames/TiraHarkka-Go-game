# Viikkoraportti 6

### Mitä olen tehnyt tällä viikolla?

- 22.8. Kirjoitin käyttöliittymän oikean valikon toiminnan. Tunti.
- 23.8. Luin Monte Carlo puuhaun toteutuksesta "Deep Learning and the Game of Go"-kirjasta. Noin 2 tuntia.
- 24.8. Tein MCPHSolmu luokan kuvaamaan Monte Carlo puuhaku puun solmuja, ja sille konstruktorin ja getterit. Muokkasin Pelitilanne luokkaa niin, että siltä saa pelitilanteen lailliset siirrot. Yhteensä 2,5 tuntia.
- 25.8. Kirjoitin MCPHSolmu luokan ja sen testit loppuun. Kirjoitin Tekoaly luokkaan Monte Carlo puuhaku algoritmin pelin simulointia vaille. Yhteensä 4 tuntia.
- 27.8. Ohjaajan kanssa Monte Carlo algoritmin toiminnan selvittelyä yms. Tunti.
- 27.8. Tein Pelitilanne luokkaan metodin laskeVoittaja joka palauttaa pelin voittajan värin. Tämä on hyvin naiivi toteutus, joka olettaa, että laudalla ei ole suurempia kuin yhden pisteen kokoisia alueita. Tunti.
- 28.8. Tekoälylle kirjoitettu metodi simuloiSattumanvarainenPeli. Noin 3 tuntia.
- 28.8. Tekoälyn lisääminen käyttöliittymään. Bugin etsintää tekoälystä. Yksi tunti.
- 29.8. Yritin korjata bugia tekoälystä. Noin 3 tuntia.

###### Käytetty aika tällä viikolla yhteensä n. 18,5 tuntia.

### Miten ohjelma on edistynyt?

- 22.8. Käyttöliittymän toiminta kirjoitettu tekoälyn lisäystä vaille.
- 24.8. Monte Carlo puuhaku puun solmuja varten aloitettu luokka.
- 25.8. Monte Carlo puuhaku puun solmuja varten kirjoitettu luokka loppuun ja sille testit. Monte Carlo puuhaun algoritmi kirjoitettu Tekoaly luokkaan.
- 27.8. Pisteiden laskuun tehty metodi Pelitilanne luokalle ja sille testit.
- 28.8. Kirjoitettu tekoälylle simuloiSattumanvarainenPeli-metodi. Jos korjattavaa ei ilmene, niin tekoälyn pitäisi olla valmis.

### Mitä opin tällä viikolla?

- 23.8. Pääidea Monte Carlo puuhausta ja eri painotuksista miten valitaan simuloitava haara.
- 25.8. Yhden teknisen tavan toteuttaa Monte Carlo puuhaku.
- 27.8. Monte Carlo algoritmin pelin simulointi selventyi, sekä syyt miten puuta rakennetaan ja miksi simulaatioita ei lisätä puuhun. Myös mahdolliset haasteet muistin kanssa, mikäli puuta säilytettäisiin siirtojen välillä.

### Mikä jäi epäselväksi tai on tuottanut vaikeuksia?

- 22.8. Pitää selvitellä miten varmistetaan, ettei käyttöliittymä ota siirtoa sillä aikaa, kun tekoäly pohtii omaa siirtoaan.
- 25.8. Miten sattumanvaraisen pelin simulointi toteutetaan niin, että sen kaikki vaiheet lisätään Monte Carlo hakupuuhun, sekä miten voisi halutulla siirrolla hyödyntää jo aikaisemmin generoitua puuta.
- 28.8. Jostain syystä käyttöliittymä jää nyt jumiin kun koitetaan klikata ensimmäistä siirtoa. Edes oma siirto ei tule näkyville, vaikka eventissä pitäisi olla ruudun päivitys ennen tekoälyn siirron hakua.
- 29.8. En jostain syystä löydä, miksi pelin simulointi jää looppiin. Jostain syystä se jossain vaiheessa simulaatiota passaa, mutta ei kahdesti peräkkäin ja vaikka tekoäly on passannut edellisellä siirrollaan, se ei passaa uusiksi seuraavalla.

### Mitä teen seuraavaksi?

- 22.8. Tekoälyn kirjoittamisen aloitus.
- 24.8. Monte Carlo puuhaku puun solmuja varten tehdyn luokan kirjoitus loppuun.
- 25.8. Pitää "naiivi" botti kirjoittaa tuottamaan sattumanvarainen peli, sekä toiminnallisuus pisteiden laskentaan, kun lopputilanne on saavutettu.
- 28.8. Etsi bugi tekoälystä, yksi null pointer exceptionin aiheuttava löydetty, mutta nyt käyttöliittymä jää jumiin, lisäämättä siirtoja.