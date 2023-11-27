import React, { useState } from 'react';
import { rateFilm } from '../utils/FilmService';

function RateFilm({ filmId }) {
  const [rating, setRating] = useState(0);

  const handleSubmit = (e) => {
    e.preventDefault();
    rateFilm(filmId, rating)
      .then(response => {
        alert('Rating submitted successfully');
      })
      .catch(error => {
        console.error('Error submitting rating', error);
      });
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <input
          type="number"
          value={rating}
          onChange={(e) => setRating(e.target.value)}
          min="1"
          max="10"
          required
        />
        <button type="submit">Rate</button>
      </form>
    </div>
  );
}

export default RateFilm;
