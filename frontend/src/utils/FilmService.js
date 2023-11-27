import axios from 'axios';

const FILM_API_URL = 'http://localhost:8080/film';
const RATINGS_API_URL = 'http://localhost:8080/ratings'; // Assuming this is the base URL for ratings

export const getFilms = () => axios.get(FILM_API_URL);
export const getFilmById = (id) => axios.get(`${FILM_API_URL}/${id}`);
export const addNewFilm = (filmData) => axios.post(FILM_API_URL, filmData);

// Updated to use the RATINGS_API_URL
export const rateFilm = (filmId, userId, rating) => {
  const ratingData = {
    filmId,
    userId,
    rating
  };
  return axios.post(RATINGS_API_URL, ratingData);
};

// Assuming you have endpoints to update and delete ratings, you can add those here as well.
