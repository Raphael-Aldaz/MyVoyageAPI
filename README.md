# MyTrainings
##Liste des routes
**Trainings**
| **Methode**  | **EndPoints**  | **Description**    |
| :--------------- |:---------------:| :-----|
| GET  |   /api/trainings        |  recupére toute les formations |
| GET  |   /api/trainings/{id}        |  recupére la formations selon l'id |
| GET  |   /api/trainings/byKeyWord/{kewWord}        |  recupére la formations selon un mot clés|
| GET  |   /api/trainings/from/{catId}        |  trie les formations par catégorie |
| GET  |   /api/categories      |  recupére toutes les categories |
| GET  |   /api/confirm     | params : liste d'articles, montantTotalCmd, customer
                            crée une order à l'aide du panier et du customer puis la sauvegarde
