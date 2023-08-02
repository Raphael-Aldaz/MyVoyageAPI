| Méthode | Endpoint                               | Description                                  |
| :-------| :-------------------------------------| :--------------------------------------------|
| GET     | /api/hotels?page=<page>                | Récupère tous les hôtels paginés.            |
| GET     | /api/hotelsBy?page=<page>&id=<id>      | Récupère les hôtels par ville en fonction de l'ID de la ville. |
| GET     | /api/hotelsByDest?page=<page>&kw=<keyword> | Recherche des villes par mot-clé et retourne les hôtels correspondants. |
| GET     | /api/hotels/<id>                       | Récupère un hôtel en fonction de l'ID.       |
| POST    | /api/hotel                             | Crée un nouvel hôtel avec les paramètres fournis. |
| PUT     | /api/hotels/<id>                       | Met à jour un hôtel en fonction de l'ID et des paramètres fournis. |
| DELETE  | /api/hotel/<id>                        | Supprime un hôtel en fonction de l'ID.       |
| GET     | /api/photo/<id>                        | Récupère la photo d'un hôtel en fonction de l'ID. |
