# Viikkoraportti 5

### Mitä olen tehnyt tällä viikolla?

- 14.8. Kirjoitin Siirto-luokan joka kuvaa seuraavaa siirtoa, sekä testit tälle luokalle. Tähän meni tunti.
- 14.8. Aloitin kirjoittamaan Pelitilanne-luokkaa ja muokkasin Ryhma, Koordinaatti ja Pelilauta luokkia lisäämällä kopiointikonstruktorit, jotta pelilaudasta saa tehtyä syväkopion. Tähän meni 2 tuntia.
- 15.8. Koordinaatti, Ryhma ja Pelilauta luokkien testit päivitetty kattamaan kopiokonstruktorit. Tunti.
- 15.8. Pelitilanne luokan lisaaSiirto, sekä peliOhi metodit kirjoitettu, sekä niille apumetodeja, sekä Pelitilanteelle testejä. Noin 3 tuntia.
- 16.8. Pelitilanne luokalle kirjoitettu lisää testejä ja pieniä muokkauksia Pelitilanteen lisaaSiirto metodiin, sekä equals Siirto luokalle. Noin 2 tuntia.
- 18.8. Pelitilanne ja Pelilauta luokat päivitetty tukemaan kirjanpitoa poistetuista kivistä. Tunti.
- 18.8. Käyttöliittymään päätoiminnallisuus toteutettu, laudan ja kivien piirto ja hiiren tapahtumakuuntelija. Noin 5 tuntia.

###### Käytetty aika tällä viikolla yhteensä n. 15 tuntia.

### Miten ohjelma on edistynyt?

- 14.8. Siirto-luokka tehty ja Pelitilanne-luokka aloitettu ja sen pelilaudan syväkopiointi toteutettu.
- 15.8. Pelitilanne luokkaa jatkettu useilla metodeilla, erityisesti siirron lisääminen laillisen siirron tarkastuksineen.
- 16.8. Pelitilanne luokka lähes valmis testeineen.
- 18.7. Loogiset luokat näiltä näkymin vaikuttavat valmiilta. Käyttöliittymän päätoiminnallisuus toteutettu.

### Mitä opin tällä viikolla?

- 14.8. Javassa syväkopiointiin on monia tapoja, itse päädyin tekemään kopiokonstruktorit.
- 18.8. Hiirenkuuntelijan toteutusta.

### Mikä jäi epäselväksi tai on tuottanut vaikeuksia?

- 14.8. Syväkopioinnin toteutustavoista en kaikkia ymmärtänyt, enkä oikein siksi osaa verrata eri toteutustapojen tehokkuutta.
- 18.8. Hiirenkuuntelijan toteutus oli hankalaa, kun piti saada klikkauksen koordinaatit ja niillä toteuttaa toimintaa.
- 18.8. Käyttöliittymän piirtoalustan tyhjennys myös jäi epäselväksi, lähinnä syötyjen kivien poisto. Päädyin piirtämään pelilaudan joka hiirenklikkauksella uudestaan ja sen päälle kivet uudestaan.

### Mitä teen seuraavaksi?

- 14.8. Pelitilanne luokan kirjoittaminen loppuun ja testien kirjoittaminen sille.
- 15.8. Pelitilanteen kanssa jatkan, eritoten testien.
- 16.8. Pitää miettiä miten kaapatut kivet lasketaan ja toteuttaa kirjanpito niistä, sekä testit tähän toiminnallisuuteen. Käyttöliittymän kirjoitus puhtaaksi niin, että voisi jo pelata pelejä.
- 18.8. Käyttöliittymän toteuttaminen loppuun niin, että sivupalkissa lisää toiminnallisuutta, mm. passaus ja luovutusmahdollisuudet ja vankimäärät näkyvät (nämä eivät vaikuta pisteytykseen, mutta helpottavat pisteidenlaskua ihmiselle kun ei tarvitse kaikkia kiviä laskea).