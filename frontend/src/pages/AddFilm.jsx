import React, { useState } from 'react';
import { addNewFilm } from '../utils/FilmService';

function AddFilm() {
  const [film, setFilm] = useState({
    title: '',
    description: '',
    releaseYear: '',
    genre: '',
    trailerUrl: '',
    posterUrl: ''
  });

  const handleChange = (e) => {
    setFilm({ ...film, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    addNewFilm(film)
      .then(response => {
        // Handle the response
        alert('Film added successfully');
      })
      .catch(error => {
        // Handle errors
        console.error('Error adding film', error);
      });
  };

  return (
    <div>
      <h2>Add a New Film</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          name="title"
          value={film.title}
          onChange={handleChange}
          placeholder="Title"
          required
        />
        {/* Repeat for other fields like description, releaseYear, genre, etc. */}
        <button type="submit">Add Film</button>
      </form>
    </div>
  );
}

export default AddFilm;
