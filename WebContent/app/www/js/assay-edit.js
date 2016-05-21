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
					removePlugins: 'bidi,dialogadvtab,div,filebrowser,flash,format,forms,horizontalrule,iframe,justify,liststyle,pagebreak,showborders,stylescombo,table,tabletools,templates',
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
						[ 'Maximize' ]

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


/*
// The trick to keep the editor in the sample quite small
// unless user specified own height.
CKEDITOR.config.height = 150;
CKEDITOR.config.width = 'auto';

var initSample = ( function() {
	var wysiwygareaAvailable = isWysiwygareaAvailable(),
		isBBCodeBuiltIn = !!CKEDITOR.plugins.get( 'bbcode' );

	return function() {
		var editorElement = CKEDITOR.document.getById( 'editor' );

		// :(((
		if ( isBBCodeBuiltIn ) {
			editorElement.setHtml(
				'Hello world!\n\n' +
				'I\'m an instance of [url=http://ckeditor.com]CKEditor[/url].'
			);
		}

		// Depending on the wysiwygare plugin availability initialize classic or inline editor.
		if ( wysiwygareaAvailable ) {
			CKEDITOR.replace( 'editor' );
		} else {
			editorElement.setAttribute( 'contenteditable', 'true' );
			CKEDITOR.inline( 'editor' );

			// TODO we can consider displaying some info box that
			// without wysiwygarea the classic editor may not work.
		}
	};

	function isWysiwygareaAvailable() {
		// If in development mode, then the wysiwygarea must be available.
		// Split REV into two strings so builder does not replace it :D.
		if ( CKEDITOR.revision == ( '%RE' + 'V%' ) ) {
			return true;
		}

		return !!CKEDITOR.plugins.get( 'widget' );
	}
} )();

// %LEAVE_UNMINIFIED% %REMOVE_LINE%
*/