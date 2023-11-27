import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import FilmList from './pages/FilmList';
import FilmDetails from './pages/FilmDetails';
import AddFilm from './pages/AddFilm';
import Header from './components/Header';

function App() {
  return (
    <Router>
      <Header />
      <Routes>
        <Route path="/" element={<FilmList />} />
        <Route path="/film/:id" element={<FilmDetails />} />
        <Route path="/add-film" element={<AddFilm />} />
      </Routes>
    </Router>
  );
}

export default App;
