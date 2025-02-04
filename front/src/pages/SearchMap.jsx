/*global kakao*/
import React, { useEffect, useState } from "react";
import Header from "../components/Header";
import styled from "styled-components";
import SearchMapLayout from "../components/SearchMapLayout";
import { useParams } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { getRoom } from "../reducers/roomSlice";
import { roomSliceActions } from "../reducers/roomSlice";

const Container = styled.div`
    width: 100%;
    height: 100%;
    position: relative;
    display: flex;
`;

const Search = styled.input`
    width: 310px;
    height: 40px;
    position: absolute;
    right: 70px;
    z-index: 2;
    font-size: 20px;
`;

const SearchForm = styled.form``;

const SearchBtn = styled.button`
    width: 50px;
    height: 45px;
    position: absolute;

    right: 20px;
    z-index: 2;
    background-color: #fff;
    color: #333;
    &:hover {
        cursor: pointer;
        background-color: #333;
        color: #fff;
    }
`;

const MainBox = styled.div`
    width: 100%;
    height: 100%;
`;
const SearchMap = () => {
    const [text, setText] = useState("");
    const dispatch = useDispatch();
    const { room } = useSelector((state) => state.room);
    const onChange = (e) => {
        setText(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        dispatch(roomSliceActions.rememberLocation(text));
        dispatch(getRoom(text));
        setText("");
    };

    return (
        <>
            <Header color="black" />
            <Container>
                <SearchForm className="inputForm" onSubmit={handleSubmit}>
                    <Search
                        placeholder="검색어를 입력하세요"
                        onChange={onChange}
                        value={text}
                    />
                    <SearchBtn type="submit">검색</SearchBtn>
                </SearchForm>

                <SearchMapLayout />
            </Container>
        </>
    );
};

export default SearchMap;
