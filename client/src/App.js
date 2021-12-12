import React, { Component } from 'react';
import Header from './components/Header';
import Layout from './components/Layout';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages/home';
import Book from './pages/book';
import Lecture from './pages/lecture';
import MyPage from './pages/mypage';
import Login from './pages/login';

const App = () => {
    return (
        <Router>
                <Header />
                <Layout>
                    <Routes>
                        <Route exact path="/book" component={Book}/>
                        <Route exact path="/lecture" component={Lecture}/>
                        <Route exact path="/mypage" component={MyPage}/>
                        <Route exact path="/home" component={Home}/>
                    </Routes>
                    <Home />
                </Layout>
        </Router>
    );
};

export default App;
