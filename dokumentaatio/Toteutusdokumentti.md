# Toteutusdokumentti

## Ohjelman rakenne

Ohjelmakoodi jakautuu kolmeen pakkaukseen:
- kayttoliittyma
- logiikka
- tekoaly

### Käyttöliittymä

Käyttöliittymä muodostuu kahdesta luokasta.
Jako näihin kahteen luokkaan on tehty JavaFX:n toiminnan varmistamiseksi.

#### Main.java

Vastaa ohjelman varsinaisen käyttöliittymän käynnistämisestä.  

#### Kayttoliittyma.java

Sisältää varsinaisen graafisen käyttöliittymän.

### Logiikka

Logiikasta vastaava pakkaus sisältää kuusi luokkaa.

#### Koordinaatti.java

Luokka kuvaa pelilaudan pistettä, sisältäen sen korkeus- ja leveyssuuntaiset arvot.
Lisäksi luokka sisältää metodin, jolta saa paluuarvona Koordinaatti-olion viereiset laudan sisällä olevat pisteet Koordinaatti-olioina.

#### Pelilauta.java

Luokan ilmentymät kuvaavat pelilaudan tilanteita. Ne pitävät kirjaa siitä, onko pelilaudan pisteissä kivi. Lisäksi ne pitävät kirjaa
pelilaudalla olevista yhtenäisistä kiviryhmistä, jotta ryhmän poisto tapahtuisi tehokkaasti.
Luokka sisältää metodin siirron lisäämiseen kyseiseen lautatilanteeseen. Tämä toiminta poistaa myös vastustajan ryhmät joille ei jää
vapauksia siirron lisäämisen seurauksena. Tämä ei kuitenkaan vielä tarkista sitä, jääkö ryhmälle, johon kivi lisätään, vapauksia.
Toiminta kuitenkin tarkistaa, että halutussa pisteessä ei ole kiveä, mutta muita laittoman siirron tyyppejä ei tarkasteta.
lisaaSiirto-metodin toimintaa voisi todennäköisesti tehostaa etsiRyhma-metodin järkevämmällä toteutuksella.

#### Pelitilanne.java

Luokan ilmentymät vastaavat pelin tilanteita, sisältäen lautatilanteen lisäksi tiedot seuraavasta pelaajasta, edellisestä siirrosta,
edellisestä pelitilanteesta, seuraavasta siirrosta ja pelin aikana kaapatuista mustista ja valkoisista kivistä.
Sisältää metodin seuraavan siirron lisäämistä varten, joka tarkastaa onko siirto laiton ja jos näin on, palauttaa uuden pelitilanteen,
jossa lautatilanne säilyy entisellään, mutta laitonta siirtoa kuvaava arvo asetettu laittoman siirron tyypin mukaan. Laillisen siirron
tilanteessa metodi palauttaa uuden pelitilanteen, jossa on pelitilanne siirron lisäämisen jälkeen.

#### Ryhma.java

Luokka pitää kirjaa yhtenäisistä yksivärisistä kiviryhmistä. Toteutuksessa ryhmällä on väri, siihen kuuluvat kivet settinä
Koordinaatti-olioita, sekä tieto ryhmän vapauksista settinä Koordinaatti-olioita.  
Tämä on sitä varten, jotta voidaan tehokkaasti tunnistaa ryhmän vapauksien määrä (vapauksista kirjaa pitävän setin koko) ja jos koko
päätyy nollaan, niin voidaan poistaa siihen kuuluvat kivet laudalta.  
Myös ryhmien yhdistämiseen löytyy metodi.

#### Siirto.java

Luokan ilmentymät kuvaavat yksittäistä siirtoa. Siirtoja on kolmen tyyppisiä, kiven lisääminen pelilaudalle, passaus ja luovutus.  
Toteutuksessa jos siirto on laudalle pelattava kivi, niin se sisältää halutun pisteen Koordinaatti-oliona ja taas passauksen ja
luovutuksen tapauksessa vastaava boolean arvo on tosi.

#### Vari.java

Enum tyyppinen luokka joka kuvaa pelattavan kiven tai pelaajan väriä, arvoina MUSTA ja VALKOINEN.

### Tekoäly

Tekoäly sisältää kaksi luokkaa.

#### MCPHSolmu.java

Luokka kuvaa Monte Carlo Puuhaku puun solmuja. Sisältää puun senhetkisen pelitilanteen, tiedot solmun vanhemmasta ja lapsista,
viimeisestä siirrosta, sekä statistiikkatietoja solmun ja sen lasten simuloitujen pelien määrästä, sekä mustan ja valkoisen voittojen
määristä simulaatioissa.

#### Tekoaly.java

Varsinainen tekoälyn toteutus. Siirtoa pyydettäessä tekoäly muodostaa Monte Carlo hakupuun käyttäen annettua pelitilannetta juurena
ja lisäämällä sille lapsia sattumanvaraisista siirroista. Algoritmi simuloi pelejä käyttäen naiivia tekoälyä ja tallentaa hakupuuhun
statistiikkatiedot sille solmulle josta simulointi lähti liikkeelle, sekä sen vanhemmalle ja vanhemman vanhemmalle jne. tiedot simuloidun
pelin voittajasta ja kasvattaa simulointien määrää. Jos solmulle ei voi lisätä enempää lapsia, niin algoritmi valitsee uct-tuloksen
perusteella parhaan lapsen, jolle se simuloi pelin.
Looppi jossa solmun valinta ja pelin simulointi tapahtuu on aikarajoitettu, joten sen aikavaativuus on O(1), eli vakioaikainen. Tämän
jälkeen parhaan lapsen valinnassa on pari peräkkäistä for-looppia, joita suoritetaan enintään lasten määrän verran. Algoritmin
aikavaativuus on siten O(n), jossa n on juurisolmun lasten lukumäärä, näitä on enintään 81, kun pelilauta on tyhjä, jolloin laillisia
siirtoja on 81 kpl.
Tällä hetkellä tekoälyn suorituskyky ei ole kovin hyvä, sillä pelejä tulee simuloitua liian vähän. Tämä johtunee Pelitilanne luokan
lisaaSiirto-metodin, sekä Pelilauta-luokan etsiRyhma-metodien tehottomuudesta. Lisäksi pelin simuloinnissa käydään kaikki lailliset siirrot
läpi jokaista siirtoa harkitessa.

## Puutteita ja bugeja

#### Pelilauta-luokan tehostaminen:

Tällä hetkellä etsiRyhma-metodi käy läpi huonoimmassa tapauksessa kaikki laudalla olevat ryhmät. Tähän voisi auttaa HashMap, jossa
avaimena on koordinaatti ja arvona ryhmä johon kyseisessä paikassa oleva kivi kuuluu. Tosin en ole täysin varma, kuinka paljon tämä
tehostaisi toteutusta, kun HashMapia pitäisi päivittää aina kiviä lisätessä, ryhmiä yhdistäessä ja ryhmiä poistettaessa.
Lisäksi tällä hetkellä siirron lisääminen joutuu tällä hetkellä tutkimaan läpi laudalla olevat ryhmät päätelläkseen onko halutun siirron
viereisessä pisteessä oleva kivi samanvärinen vai erivärinen. Tätä voisi tehostaa sillä, että Koordinaatti[][] sijasta Pelilauta
tallentaisi byte[][] taulukkoon tiedon onko ruudussa musta kivi, valkoinen kivi vai onko ruutu tyhjä.

#### Pelitilanne-luokan tehostaminen:

Laittomien siirtojen tarkistusta erityisesti ko-tilanteen tarkistuksessa voisi luultavasti tehostaa Zobrist hajautusta käyttäen. Tällä
hetkellä tarkistus tapahtuu käymällä läpi kaikki aikaisemmat pelitilanteet ja vertaamalla siirron lisäämisen jälkeistä lautatilannetta
aikaisempien pelitilanteiden lautatilanteisiin.
Zobrist hajautuksessa kun lisätään uusi siirto, niin lasketaan siirtoa edeltävän tilanteen hajautusarvon perusteella uusi hajautusarvo
pelitilanteelle xor operaatiolla, lisäämällä koordinaateille annettu hajautusarvo siirtoa edeltävän tilanteen hajautusarvoon ja lisäksi
lisätään xor operaatiolla jokaisen poistetun kiven hajautusarvo samaten. Tämän jälkeen tarkistetaan onko samaa hajautusarvoa jo
tallennettuna pelitilanteen hajautusarvoista kirjaa pitävään settiin, jos on niin siirto rikkoo ko-sääntöä, jos ei ole ja muut laillisen
siirron tarkistukset on jo tehtynä, niin siirto on laillinen ja palautetaan uusi pelitilanne johon on lisätty uusi siirto ja lisäksi
sen hajautusarvoista kirjaa pitävään settiin lisätty tämä uusi hajautusarvo.

#### Bugi Tekoaly-luokassa:

Tekoäly luokan rivi 71 ei ole täysin testattu. Yksi haara jää automaattisten testien ulkopuolelle. Empiirisessä testauksessa huomasin,
että tällä rivillä aiheutuu NullPointerException tilanteessa, jossa tekoälyn pitäisi valita siirroksi passaus, eli kun pelilaudalla ei
ole jäljellä laillisia siirtoja, jotka eivät täytä omaa silmää. Tästä en oikein tiedä miten tämän korjaisi ja haluaisin ymmärtää tätä
paremmin, mutta minulla ei ole ollut aikaa perehtyä tähän ongelmaan.

#### Bugi Tekoaly-luokan ja Käyttöliittymän yhteistoiminnassa:

Empiirisissä kokeissa havaitsin, että jos pyrkii tulokseen, jossa päädytään pisteidenlaskentaan, eli tekoäly ei luovuta, niin
käyttöliittymän valkean kaappaamien kivien määrä saattaa kasvaa vaikka valkea ei kiviä kaappaisi. Tämä saattaa johtua siitä, että
simulaatio käyttää pelitilannetta ja päivittää siihen kaapattujen kivien määrää simulaatioiden aikana tapahtuneista kaappauksista. Tämä
todennäköisesti ratkeaa sillä, että tekoäly tekee syväkopion pelitilanteesta ennen kuin alkaa käyttämään sitä pelien simulointiin.

## Laajennuksia

#### Pelilaudan koon valinta

Pelilaudan koon valinnan lisääminen ja toteutuksen muokkaaminen niin, että voi pelata erikokoisilla laudoilla, ainakin 13x13 ja 19x19
tämän 9x9 toteutuksen lisäksi.

#### Värien valinta

Mahdollisuus valita molemmille väreille joko ihmis- tai tekoälypelaaja, mikä mahdollistaisi kahden ihmispelaajan väliset pelit, ihmisen
pelaamisen valkoisilla mustaa tekoälyä vastaan tai self-play toiminnallisuus tekoälylle, eli kahden tekoälyn pelaaminen vastakkain.

#### Nettiliitäntämahdollisuus

GTP (Go Text Protocol) lisääminen toiminnallisuuteen, jotta ohjelman tekoälyn voi yhdistää yleisiin Go servereihin, kuten KGS tai OGS.
Tätä protokollaa voi käyttää myös siihen, että oma tekoäly pelaa muita tekoälyjä kuten GNU Go tai Pachi vastaan. Tätä voi käyttää
mahdollisesti hyödyksi jos käyttää tekoälyjen välisiä pelejä harjoittamaan koneoppimista hyödyntävää tekoälyä.

#### Koneoppimista hyödyntävän tekoälyn lisääminen

Tässä voi käyttää apuna edellisiä laajennuksia.

## Lähteet

- https://www.cs.helsinki.fi/u/ahslaaks/kkkk.pdf
Käytin aivan projektin alkuvaiheessa määrittelydokumenttia tehdessä, kun pohdin
aikavaativuuksia.

- https://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.699.1458&rep=rep1&type=pdf
Käytin projektin alussa hahmottamaan itselleni
kuinka Monte Carlo Puuhaku-algoritmi pääpiirteissään toimii.

- Pumperla, M., Ferguson, K., 2019, Deep Learning and the Game of Go, Manning Publications
Deep Learning and the Game of Go on ollut päälähteeni koko projektin ajan aivan alkua lukuunottamatta.