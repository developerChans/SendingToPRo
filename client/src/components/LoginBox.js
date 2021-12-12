import React, { useState } from 'react';
import styled from 'styled-components';

const LoginBox = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  return (
    <Container>
      <Title>로그인</Title>
      <Box>
        <Section>
          <Input
            type='text'
            placeholder='이메일'
            value={email}
            onChange={({ target: { email } }) => {
              setEmail(email);
            }}
            required
          />
        </Section>
        <Section>
          <Input
            type='text'
            placeholder='비밀번호'
            value={password}
            onChange={({ target: { password } }) => {
              setPassword(password);
            }}
            required
          />
        </Section>
        <Section>
          <Find>아이디 찾기  ㅣ  비밀번호 찾기  ㅣ  회원 가입</Find>
        </Section>
      </Box>
        <Section>
            <Button>로그인</Button>
        </Section>
    </Container>
  )
}

const Container = styled.section`
  display: flex;
  flex-direction: column;
  border-radius: 35px;
  align-items: center;
  margin: auto;
  background-color: white;
  width: 564px;
  height: 380px;
  box-shadow: 0px 8px 8px rgba(0, 0, 0, 0.25);
`;

const Title = styled.span`
  font-weight: 700;
  font-size: 2.125rem;
  color: #383838;
  margin-top: 30px;
`;

const Box = styled.div`
  width: 100%;
  margin: 15px 0px;
`;

const Section = styled.section`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
`;

const Input = styled.input`
  margin-top: 10px;
  border: 2px solid #D4D4D4;
  margin-bottom: 15px;
  width: 506px;
  height: 45px;
  padding: 5px;
  border-radius: 10px;
  outline-style: none;
  background-color: inherit;
`;

const Find = styled.span`
  font-size: 14px;
  margin: 5px;
  color: #383838;
`;

const Button = styled.button`
  margin: 0;
  width: 179px;
  height: 51px;
  border: none;
  cursor: pointer;
  font-size: 1rem;
  padding: 12px 16px;
  border-radius: 30px;
  background: #A17DD9;
  color: #ffffff;

  &:active,
  &:hover,
  &:focus {
    background: #7731E3;
  }

  &:disabled {
    cursor: default;
    opacity: 0.5;
    background: #7731E3;
  }
`;

export default LoginBox;