// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";


import { getFirestore } from "firebase/firestore";

const firebaseConfig = {
  apiKey: "AIzaSyB5-SqEnllGNCW9Ojr0fWP50QGRfFvrXQ8",
  authDomain: "crud-fire-react-75422.firebaseapp.com",
  projectId: "crud-fire-react-75422",
  storageBucket: "crud-fire-react-75422.firebasestorage.app",
  messagingSenderId: "897605751630",
  appId: "1:897605751630:web:4e7dd7915e875c3141841f"
};


const app = initializeApp(firebaseConfig);

export const db = getFirestore(app)