# modyo-pokemon-backend

# RETO MODYO POKEMON API

**Endpoints**

Historia de Usuario 01 - POKEMONS endpoint
http://{IP-Externa}:8080/pokemon

Historia de Usuario 02 - POKEMON endpoint
http://{IP-Externa}:8080/pokemon/{nombre-pokemon}

**Por favor, contactar el publicador para el valor de la {IP-Externa}.**

# COMO UTILIZAR ESTA API

**Endpoint http://{IP-Externa}:8080/pokemon**

Este endpoint devuelve una lista de pokemons (registros) con su descripción básica.

Cómo puedo utilizar este endpoint?

Sin paginación
http://{IP-Externa}:8080/pokemon

Con paginación
http://{IP-Externa}:8080/pokemon?offset=20&limit=20
'offset' representa la cantidad de registros que API va a obviar para devolver una respuesta desde el registro representado por offset + 1.
'limit' representa el número de registros que la API debe deveolver como respuesta.

Requests
Url: http://localhost:8080/pokemon
Url: http://localhost:8080/pokemon?offset=20&limit=20

Response (request usado 'http://localhost:8080/pokemon?offset=0&limit=1')

{
    "pokemons": [
        {
            "name": "bulbasaur",
            "weight": 69,
            "types": [
                "grass",
                "poison"
            ],
            "abilities": [
                "overgrow",
                "chlorophyll"
            ],
            "photo": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
        }
    ],
    "count": 1154,
    "next": {
        "offset": 1,
        "limit": 1
    },
    "previous": null
}

**Endpoint http://{IP-Externa}:8080/pokemon/{nombre-pokemon}**

Este endpoint devuelve la información basica de un pokemon con su descripción extendida.

Cómo puedo utilizar este endpoint?

Endpoint http://{IP-Externa}:8080/pokemon/{nombre-pokemon}
El valor 'nombre-pokemon' es el encontrado en la lista de pokemons bajo la propiedad 'name'. Por ejempo, en "name": "bulbasaur", el valor a utilizar es 'bulbasaur'.

Requests
Url: http://{IP-Externa}:8080/pokemon/bulbasaur

Respuesta

{
    "pokemon": {
        "name": "bulbasaur",
        "weight": 69,
        "types": [
            "grass",
            "poison"
        ],
        "abilities": [
            "overgrow",
            "chlorophyll"
        ],
        "photo": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
    },
    "descriptions": [
        {
            "description": "There is a plant seed on its back right\nfrom the day this POKéMON is born.\nThe seed slowly grows larger.",
            "versions": [
                "firered"
            ]
        }
    ],
    "evolutions": [
        "bulbasaur",
        "ivysaur",
        "venusaur"
    ],
    "evolvedFrom": null,
    "color": "green",
    "shape": "quadruped",
    "habitat": "grassland",
    "generation": "generation-i",
    "genus": "Seed Pokémon"
}

Notese que la descripción extendida incluye datos específicos del pokemon y de su especie ya que a partir de la versión 2 de PokeAPI, la descripción del pokemon se incluye dentro de la especie, basado en la version del juego, asi que se incluye cada descripción única y las versiones a la cual esa descripción aplica.

# DESPLIEGUE

- Plataforma: Google Cloud Platform (GCP)
- Proyecto: pokemon-backend

# REQUERIMIENTOS para completar el despliegue
Para este despliegue se utiliza Kubernetes.
- Un proyecto en GCP
- Empaquetado de la aplicación
- Una imagen container de la aplicación
- Un cluster de Kubernetes
- Un servicio despliegue del container
- Un servicio load balancer para exponer el despliegue a la internet

# PROCESO

I. Abrir la terminal de **cloud shell**

II. Clonar el repositorio, ejecutando...
> git clone https://github.com/carmenper/modyo-pokemon-backend.git

III. Moverse a la carpeta del repositorio, ejecutando...
> cd modyo-pokemon-backend

IV. Asegurarse que el comando **mvnw** pueda ejecutarse usando **chmod**
> chmod +x mvnw

V. Empaquetar la aplicación, ejecutando...
> ./mvnw -DskipTests package

VI. Asegurarse de tener activado el servicio de container registry de GCP, ejecutando...
> gcloud services enable containerregistry.googleapis.com

VII. Crear una variable de ambiente llamada 'GOOGLE_CLOUD_PROJECT', a ser usada para crear el contenedor, ejecutando...
> export GOOGLE_CLOUD_PROJECT='gcloud config list --format="value(core.project)"'

VIII. Crear el contenedor, ejecutando...
> ./mvnw -DskipTests com.google.cloud.tools:jib-maven-plugin:build \
>  -Dimage=gcr.io/$GOOGLE_CLOUD_PROJECT/pokemon-backend-container:v1

IX. Asegurarse de tener activado el servicio de compute y container de GCP, ejecutando...
> gcloud services enable compute.googleapis.com container.googleapis.com

X. Crear del cluster de Kubernetes, ejecutando...
> gcloud container clusters create pokemon-backend-cluster \
>  --num-nodes 2 \
>  --machine-type n1-standard-1 \
>  --zone us-central1-c

XI. Crear un servicio de despliegue del contenedor, ejecutando...
> kubectl create deployment pokemon-backend-deployment \
>  --image=gcr.io/$GOOGLE_CLOUD_PROJECT/pokemon-backend-container:v1

XII. Crear un servicio load balancer para exponer el servicio de despliegue a la internet, ejecutando...
> kubectl create service loadbalancer pokemon-backend-deployment --tcp=8080:8080

XIII. Verificar que aplicacion puede ser accesada desde la internet (curl/postman) utilizando la IP-Externa proveida por el load balancer para completar los endpoints al principio de este documento.


