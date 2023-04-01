# Spotify REST API en Kotlin

Este repositorio contiene un proyecto de ejemplo que muestra cómo utilizar la API de Spotify en Kotlin. Esta implementación utiliza la biblioteca [spotify-web-api-kotlin](https://github.com/adamint/spotify-web-api-kotlin) para interactuar con la API de Spotify.

## Requisitos previos

Antes de comenzar, asegúrate de tener lo siguiente:

- Una cuenta de Spotify
- Un ID de cliente y una clave secreta de la API de Spotify. Puedes obtenerlos en el [panel de control de Spotify para desarrolladores](https://developer.spotify.com/dashboard/).

## Instalación

1. Clona este repositorio en tu máquina local.
2. Abre el proyecto en tu IDE favorito.
3. Agrega tus credenciales de la API de Spotify al archivo `application.conf`.
4. Ejecuta la aplicación.

## Uso

La aplicación se ejecuta en `http://localhost:8080`. Puedes utilizar las siguientes rutas para interactuar con la API:

- `/search?q={query}`: busca canciones, artistas o álbumes por nombre. Ejemplo: `/search?q=The Beatles`
- `/artists/{id}`: obtiene información sobre un artista específico. Ejemplo: `/artists/avicii`
- `/albums/{id}`: obtiene información sobre un álbum específico. Ejemplo: `/albums/true`
- `/tracks/{id}`: obtiene información sobre una canción específica. Ejemplo: `/tracks/wake me up`
- `/profiles/{id}`: obtiene información sobre un perfil específico. Ejemplo: `/profiles/javier`

También puedes utilizar la colección de Postman adjunta en este repositorio para lanzar las peticiones.

---

# Spotify REST API in Kotlin

This repository contains a sample project that demonstrates how to use the Spotify API in Kotlin. This implementation uses the [spotify-web-api-kotlin](https://github.com/adamint/spotify-web-api-kotlin) library to interact with the Spotify API.

## Prerequisites

Before you begin, make sure you have the following:

- A Spotify account
- A client ID and secret key for the Spotify API. You can obtain them from the [Spotify Developer Dashboard](https://developer.spotify.com/dashboard/).

## Installation

1. Clone this repository to your local machine.
2. Open the project in your favorite IDE.
3. Add your Spotify API credentials to the `application.conf` file.
4. Run the application.

## Usage

The application runs at `http://localhost:8080`. You can use the following routes to interact with the API:

- `/search?q={query}`: search for songs, artists, or albums by name. Example: `/search?q=The Beatles`
- `/artists/{id}`: get information about a specific artist. Example: `/artists/avicii`
- `/albums/{id}`: get information about a specific album. Example: `/albums/true`
- `/tracks/{id}`: get information about a specific track. Example: `/tracks/wake me up`
- `/profiles/{id}`: get information about a specific profile. Example: `/profiles/javier`

You can also use the Postman collection attached in this repository to make requests.