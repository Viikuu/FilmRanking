import React, { useEffect, useState } from 'react';
import { getFilmById } from '../utils/FilmService';

function FilmDetails({ match }) {
  const [film, setFilm] = useState(null);

  useEffect(() => {
    const filmId = match.params.id;
    getFilmById(filmId).then(response => {
      setFilm(response.data);
    });
  }, [match.params.id]);

  if (!film) return <div>Loading...</div>;

  return (
    <div>
      <h2>{film.title}</h2>
      <p>{film.description}</p>
      {/* Display other film details */}
    </div>
  );
}

export default FilmDetails;
