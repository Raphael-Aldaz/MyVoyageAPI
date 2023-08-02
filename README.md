# Routes du contrôleur HotelController
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

# Routes du contrôleur UsersController

| Méthode | Endpoint                       | Description                                              |
| :-------| :----------------------------- | :--------------------------------------------------------|
| GET     | /api/users?page=<page>         | Récupère tous les utilisateurs paginés.                 |
| DELETE  | /api/user/<id>                 | Supprime un utilisateur en fonction de l'ID.            |
| POST    | /api/user                      | Crée un nouvel utilisateur avec les paramètres fournis. |

# Routes du contrôleur CityController

| Méthode | Endpoint          | Description                                  |
| :-------| :---------------- | :--------------------------------------------|
| GET     | /api/cities       | Récupère toutes les villes.     
