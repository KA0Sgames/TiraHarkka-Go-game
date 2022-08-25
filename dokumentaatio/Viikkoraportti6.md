# Viikkoraportti 6

### Mitä olen tehnyt tällä viikolla?

- 22.8. Kirjoitin käyttöliittymän oikean valikon toiminnan. Tunti.
- 23.8. Luin Monte Carlo puuhaun toteutuksesta "Deep Learning and the Game of Go"-kirjasta. Noin 2 tuntia.
- 24.8. Tein MCPHSolmu luokan kuvaamaan Monte Carlo puuhaku puun solmuja, ja sille konstruktorin ja getterit. Muokkasin Pelitilanne luokkaa niin, että siltä saa pelitilanteen lailliset siirrot. Yhteensä 2,5 tuntia.
- 25.8. Kirjoitin MCPHSolmu luokan ja sen testit loppuun. Kirjoitin Tekoaly luokkaan Monte Carlo puuhaku algoritmin pelin simulointia vaille. Yhteensä 4 tuntia.

###### Käytetty aika tällä viikolla yhteensä n. 9,5 tuntia.

### Miten ohjelma on edistynyt?

- 22.8. Käyttöliittymän toiminta kirjoitettu tekoälyn lisäystä vaille.
- 24.8. Monte Carlo puuhaku puun solmuja varten aloitettu luokka.
- 25.8. Monte Carlo puuhaku puun solmuja varten kirjoitettu luokka loppuun ja sille testit. Monte Carlo puuhaun algoritmi kirjoitettu Tekoaly luokkaan.

### Mitä opin tällä viikolla?

- 23.8. Pääidea Monte Carlo puuhausta ja eri painotuksista miten valitaan simuloitava haara.
- 25.8. Yhden teknisen tavan toteuttaa Monte Carlo puuhaku.

### Mikä jäi epäselväksi tai on tuottanut vaikeuksia?

- 22.8. Pitää selvitellä miten varmistetaan, ettei käyttöliittymä ota siirtoa sillä aikaa, kun tekoäly pohtii omaa siirtoaan.
- 25.8. Miten sattumanvaraisen pelin simulointi toteutetaan niin, että sen kaikki vaiheet lisätään Monte Carlo hakupuuhun, sekä miten voisi halutulla siirrolla hyödyntää jo aikaisemmin generoitua puuta.

### Mitä teen seuraavaksi?

- 22.8. Tekoälyn kirjoittamisen aloitus.
- 24.8. Monte Carlo puuhaku puun solmuja varten tehdyn luokan kirjoitus loppuun.
- 25.8. Pitää "naiivi" botti kirjoittaa tuottamaan sattumanvarainen peli, sekä toiminnallisuus pisteiden laskentaan, kun lopputilanne on saavutettu.