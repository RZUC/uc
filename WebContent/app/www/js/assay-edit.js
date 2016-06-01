/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

/* exported initSample */

if ( CKEDITOR.env.ie && CKEDITOR.env.version < 9 )
	CKEDITOR.tools.enableHtml5Elements( document );


CKEDITOR.replace( 'editor', {
					extraPlugins: 'bbcode',
					// Remove unused plugins.
					// removePlugins: 'bidi,dialogadvtab,div,filebrowser,flash,format,forms,horizontalrule,iframe,justify,liststyle,pagebreak,showborders,stylescombo,table,tabletools,templates',
					// Width and height are not supported in the BBCode format, so object resizing is disabled.
					disableObjectResizing: true,
					// Define font sizes in percent values.
					fontSize_sizes: "30/30%;50/50%;100/100%;120/120%;150/150%;200/200%;300/300%",
					toolbar: [
						[ 'Source','-', 'Undo', 'Redo' ],
						[ 'Find', 'Replace', '-', 'SelectAll', 'RemoveFormat' ],
						[ 'Link', 'Unlink', 'Image', 'Smiley', 'SpecialChar' ],
						[ 'Bold', 'Italic', 'Underline' ],
						[ 'FontSize' ],
						[ 'TextColor' ],
						[ 'NumberedList', 'BulletedList', '-', 'Blockquote' ],
						[ 'Maximize' ],
						['Save']

					],
					// Strip CKEditor smileys to those commonly used in BBCode.
					smiley_images: [
						'regular_smile.png', 'sad_smile.png', 'wink_smile.png', 'teeth_smile.png', 'tongue_smile.png',
						'embarrassed_smile.png', 'omg_smile.png', 'whatchutalkingabout_smile.png', 'angel_smile.png',
						'shades_smile.png', 'cry_smile.png', 'kiss.png'
					],
					smiley_descriptions: [
						'smiley', 'sad', 'wink', 'laugh', 'cheeky', 'blush', 'surprise',
						'indecision', 'angel', 'cool', 'crying', 'kiss'
					]
				});


console.log(CKEDITOR)
console.log(CKEDITOR.dom)
console.log(CKEDITOR.dom.text())

