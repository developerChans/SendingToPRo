import React from 'react';
import styled from 'styled-components';
import Header from './Header';
import { media } from '../lib/style-utils';

const Layout = ({children}) => (
    <>
        <Section>{children}</Section>
    </>
);

Layout.Main = styled.div`
    margin: 0 auto;
    margin-top: 2rem;
    width: 1200px;
    transition: all .3s;
    position: relative;
    
    ${media.desktop`
        width: 990px;
    `}

    ${media.tablet`
        margin-top: 1rem;
        width: calc(100% - 2rem);
    `}

    ${media.mobile`
        margin-top: 0.5rem;
        width: calc(100% - 1rem);        
    `}

`

const Section = styled.section`
    display: flex;
    flex-direction: column;
    width: 100%;
    min-height: calc(100vh - 128px);
    margin: 0px;
    padding: 0px;
    background-color: #ECE8F2;
    align-items: center;
`;

export default Layout;