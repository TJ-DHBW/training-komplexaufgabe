# S59 - Getränkeabfüllung

#### Patterns
- Proxy
- Observer
- Command

### TODOs
- Username and pasword stored on proxy
- password encrypted (SHA-256)
- Drink
    - 10% concentration (c)
    - 90% Water (w)
- ~~Bottle~~
    - ~~Holds 330 chars from Pool \[d]~~
- ~~Pallet~~
    - ~~Bottle\[25]\[20]\[15] (L,B,H)~~
    - Capacity: 6000 Bottles ???
- ~~CentralStorage~~
    - ~~StoragePlace\[10]\[10] (L,B)~~
    - ~~Initially filled with 300 Pallets (100%)~~
- ~~StoragePlace~~
    - ~~Holds 3 Pallets~~

- Robot
    - R01: Transport to station
    - R02: Fill station with empty bottles
        - takes 25 empty bottles from Pallet (B:1,L:25)
        - turn by 90° (B:25,L:1)
        - put bottles in lane of station
        - Observe lane with sensor and fill again
- BottlingPlant
    - 25 Slots
    - connected to T01(Concentrate, L:100,B:100,H:100) via hose
    - connected to T02(Water, L:1000,B:500,H:500) via hose
    - (implied: fills the bottles)
- Tank
- Hose
- ControlCenter
    - observe level of tanks with sensor
    - notify tanklaster on 15% concentrate and/or 20% water

- TankTruck
    - Capacity: 25000
    - on notify fill the right tank


## Full Task

Eine Getränkeabfüllung wird zentral von einem Kontrollcenter über eine Konsole mit den Kommandos Login(user,password), Start, Order(type) und Info gesteuert. Der Benutzername und das Passwort sind zentral auf einem Proxy gespeichert. Das Password ist mit SHA-256 verschlüsselt. Ein Getränk besteht aus  10%  Konzentrat  (c)  und  90%  Wasser  (w).  Eine  Flasche  hat  ein  Fassungsvermögen  von  330 Zeichen aus dem Pool [d]. Die leeren Flaschen werden auf Paletten – dargestellt als Matrix [L: 25, B: 20, H: 15] – gelagert. Eine Palette hat eine Kapazität von 6000 leeren Flaschen. In einem Zentrallagerexistieren 100 Lagerplätze (L: 10, B: 10). Ein Lagerplatz hat eine Kapazität für drei gestapelte Paletten. Initial ist das Zentrallager mit 300 Paletten zu 100% gefüllt. Die Paletten werden sukzessive von einem Roboter  R01  zu  einer  Station  transportiert.  Ein  Roboter  R02  ist  verantwortlich  für  die  Bestückung  der Anlage mit leeren Flaschen. R02 nimmt eine Reihe mit 25 Flaschen [B: 1, L: 25] von der Palette, dreht diese um 90° [H: 25, B: 1] und setzt die Flaschen als Linie in die Spur der Abfüllanlage. Die rotierende Abfüllanlage mit 25 Slots übernimmt die 25 Flaschen. R02 beobachtet mit einem Sensor die Spur und befüllt diese erneut. Die Abfüllanlage ist mit je einem Schlauch an den Tank T01 für Konzentrat [L: 100, B:  100,  H:  100]  und Tank T02  für  Wasser  [L:1000,  B:  500,  H:  500]  angeschlossen.  Ein  Kontrollcenter beobachtet den Füllstand mit je einem Sensor. Ein Tanklaster hat eine maximale Kapazität von 25000. Bei  einem  Füllstand  von  15%  Konzentrat  und  20%  Wasser  werden  die Tanklaster  vom  Kontrollcenter beauftragt die notwendige Menge für die vollständige Befüllung des Tank T01 oder T02 zu liefern. Der korrespondierende Tank wird befüllt und die Befüllung der Flaschen fortgesetzt.