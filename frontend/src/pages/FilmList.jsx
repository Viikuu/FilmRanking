import React, { useEffect, useState } from 'react';
import RateFilm from '../components/RateFilm';
import { getFilms } from '../utils/FilmService';

function FilmList({ isLoggedIn }) {
  const [films, setFilms] = useState([]);

  useEffect(() => {
    getFilms().then(response => {
      setFilms(response.data);
    });
  }, []);

  return (
    <div>
      {films.map(film => (
        <div key={film.id}>
          <h3>{film.title}</h3>
          <p>Genre: {film.genre}</p>
          <p>Year: {film.releaseYear}</p>
          <p>Rating: {film.meanRating}</p>
          {isLoggedIn && <RateFilm filmId={film.id} />}
        </div>
      ))}
    </div>
  );
}

export default FilmList;
