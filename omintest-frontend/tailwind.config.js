/** @type {import('tailwindcss').Config} */

import defaultTheme from 'tailwindcss/defaultTheme'

const constants = {
    burger: '#222',
    customBlack: '#1b1b1b',
    customGreen: '#1b9c6a',
    lightGreen: '#67c09e',
    customRed: '#ec2020',
    customGray: '#afafaf',
    darkGray: '#343735',
    bgCardHover: '#f9fffd',
    bgScrollBarTrack: '#f1f1f1',
    bgScrollBarThumb: '#d4d4d4',
    bgFieldEye: 'rgba(144, 202, 249, 0.2)',
    bgOverlay: 'rgba(0, 0, 0, 0.25)'
}

export default {
    important: true,
    content: [
        './index.html',
        './src/**/*.{js,ts,jsx,tsx}'
    ],
    theme: {
        fontFamily: {
            'inter': ['Inter', 'sans-serif'],
            'interBold': ['InterBold', 'sans-serif']
        },
        extend: {
            colors: {
                transparent: 'transparent',
                current: 'currentColor',
                ...constants
            },
            boxShadow: {
                '3xl': '0 35px 60px -15px rgba(0, 0, 0, 0.3)',
                'cardHover': '0px 0px 15px 0px rgba(0, 0, 0, 0.1)',
                'switch': '0 0 5px #67c09e'
            },
            textShadow: {
                '3xl': '0 35px 60px -15px rgba(0, 0, 0, 0.3)'
                // 'card': '-5px 5px 10px 5px rgba(0, 0, 0, 0.3)',
                // 'auth': '0px 2px 24px 0px rgba(0, 0, 0, 0.04)'
            },
            animation: {},
            keyframes: {},
            transitionProperty: {
                'button-test': 'background-color, box-shadow',
                'input-eye': 'scale'
            },
            borderRadius: {
                '10': '10px',
                '5': '5px',
                '3': '3px',
                '2': '2px'
            },
            scale: {
                '97': '0.97',
                '94': '0.94'
            },
            spacing: {
                '5p': '5px',
                '10p': '10px',
                '14p': '14px',
                '15p': '15px'
            },
            fontSize: {
                '12p': '12px',
                '16p': '16px'
            },
            gridTemplateColumns: {
                'cards-grid': 'repeat(auto-fit, 270px)'
            }

        },
        screens: {
            'lg': '1280px',
            ...defaultTheme.screens
        }
    },
    plugins: []
}

