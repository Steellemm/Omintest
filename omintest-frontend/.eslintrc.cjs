module.exports = {
    root: true,
    env: {browser: true, es2020: true},
    extends: [
        'eslint:recommended',
        'plugin:@typescript-eslint/recommended',
        'plugin:react-hooks/recommended',
    ],
    ignorePatterns: ['dist', '.eslintrc.cjs'],
    parser: '@typescript-eslint/parser',
    plugins: ['react-refresh'],
    rules: {
        "max-len": ["warn", 150],
        "indent": ["warn", 4],
        "react/forbid-prop-types": 0,
        "semi": [2, "never"],
        "no-underscore-dangle": 0,
        "no-console": 0,
        "linebreak-style": 0,
        "comma-dangle": [2, {
            "arrays": "never",
            "objects": "never",
            "imports": "never",
            "exports": "never",
            "functions": "ignore"
        }],
        "quotes": ["error", "single"],
        "object-curly-spacing": [2, "always"],
        'react-refresh/only-export-components': [
            'warn',
            {allowConstantExport: true},
        ],
        "react/react-in-jsx-scope": "off",
    },
}
