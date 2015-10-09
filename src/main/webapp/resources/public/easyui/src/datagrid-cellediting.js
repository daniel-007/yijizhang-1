(function ($) {
    $.extend($.fn.datagrid.defaults, {
        clickToEdit: true,
        dblclickToEdit: false,
        navHandler: {
            '37': function (e) {
                var opts = $(this).datagrid('options');
                return navHandler.call(this, e, opts.isRtl ? 'right' : 'left');
            },
            '39': function (e) {
                var opts = $(this).datagrid('options');
                return navHandler.call(this, e, opts.isRtl ? 'left' : 'right');
            },
            '38': function (e) {
                return navHandler.call(this, e, 'up');
            },
            '40': function (e) {
                return navHandler.call(this, e, 'down');
            },
            '13': function (e) {
                return doneHandler.call(this, e, true);
            },
            '27': function (e) {
                return doneHandler.call(this, e, false);
            },
            '8': function (e) {
                return clearHandler.call(this, e);
            },
            '46': function (e) {
                return clearHandler.call(this, e);
            },
            'keypress': function (e) {
                var dg = $(this);
                var param = dg.datagrid('cell');	// current cell information
                if (!param) {
                    return;
                }
                var input = dg.datagrid('input', param);
                if (!input) {
                    var tmp = $('<span></span>');
                    tmp.html(String.fromCharCode(e.which));
                    var c = tmp.text();
                    tmp.remove();
                    if (c) {
                        dg.datagrid('editCell', {
                            index: param.index,
                            field: param.field,
                            value: c
                        });
                        return false;
                    }
                }
            }
        },
        onCellEdit: function (index, field, value) {
            var input = $(this).datagrid('input', {index: index, field: field});
            if (input) {
                if (value != undefined) {
                    input.val(value);
                }
            }
        }
    });

    function navHandler(e, dir) {
        var dg = $(this);
        var param = dg.datagrid('cell');
        var input = dg.datagrid('input', param);
        if (!input) {
            dg.datagrid('gotoCell', dir);
            return false;
        }
    }

    function doneHandler(e, accepted) {
        var dg = $(this);
        var param = dg.datagrid('cell');
        if (!param) {
            return;
        }
        var input = dg.datagrid('input', param);
        if (input) {
            if (accepted) {
                if (dg.datagrid('validateRow', param.index)) {
                    dg.datagrid('endEdit', param.index).datagrid('gotoCell', param);
                } else {
                    input.focus();
                }
            } else {
                dg.datagrid('cancelEdit', param.index).datagrid('gotoCell', param);
            }
        } else if (accepted) {
            dg.datagrid('editCell', param);
        }
        return false;
    }

    function clearHandler(e) {
        var dg = $(this);
        var param = dg.datagrid('cell');
        if (!param) {
            return;
        }
        var input = dg.datagrid('input', param);
        if (!input) {
            dg.datagrid('editCell', {
                index: param.index,
                field: param.field,
                value: ''
            });
            return false;
        }
    }

    function getCurrCell(target) {
        var cell = $(target).datagrid('getPanel').find('td.datagrid-row-selected');
        if (cell.length) {
            return {
                index: parseInt(cell.closest('tr.datagrid-row').attr('datagrid-row-index')),
                field: cell.attr('field')
            };
        } else {
            return null;
        }
    }

    function gotoCell(target, p) {
        var dg = $(target);
        var opts = dg.datagrid('options');
        var panel = dg.datagrid('getPanel').focus();

        var cparam = dg.datagrid('cell');
        if (cparam) {
            var input = dg.datagrid('input', cparam);
            if (input) {
                input.focus();
                return;
            }
        }

        var cell = panel.find('td.datagrid-row-selected');
        if (typeof p == 'object') {
            cell.removeClass('datagrid-row-selected');
            cell = opts.finder.getTr(target, p.index).find('td[field="' + p.field + '"]');
            cell.addClass('datagrid-row-selected');
            return;
        }
        if (!cell) {
            return;
        }
        var fields = dg.datagrid('getColumnFields', true).concat(dg.datagrid('getColumnFields'));
        var field = cell.attr('field');
        var tr = cell.closest('tr.datagrid-row');
        var rowIndex = parseInt(tr.attr('datagrid-row-index'));
        var colIndex = $.inArray(field, fields);

        if (p == 'up' && rowIndex > 0) {
            rowIndex--;
        } else if (p == 'down' && rowIndex < dg.datagrid('getRows').length - 1) {
            rowIndex++;
        } else if (p == 'left') {
            var i = colIndex - 1;
            while (i >= 0) {
                var col = dg.datagrid('getColumnOption', fields[i]);
                if (!col.hidden) {
                    colIndex = i;
                    break;
                }
                i--;
            }
        } else if (p == 'right') {
            var i = colIndex + 1;
            while (i <= fields.length - 1) {
                var col = dg.datagrid('getColumnOption', fields[i]);
                if (!col.hidden) {
                    colIndex = i;
                    break;
                }
                i++;
            }
        }

        field = fields[colIndex];
        var td = opts.finder.getTr(dg[0], rowIndex).find('td[field="' + field + '"]');
        if (td.length) {
            dg.datagrid('scrollTo', rowIndex);
            cell.removeClass('datagrid-row-selected');
            td.addClass('datagrid-row-selected');

            var body2 = dg.data('datagrid').dc.body2;
            var left = td.position().left;
            if (left < 0) {
                body2._scrollLeft(body2._scrollLeft() + left * (opts.isRtl ? -1 : 1));
            } else if (left + td._outerWidth() > body2.width()) {
                body2._scrollLeft(body2._scrollLeft() + (left + td._outerWidth() - body2.width()) * (opts.isRtl ? -1 : 1));
            }
        }
    }

    function editCell(target, param) {
        var dg = $(target);
        var opts = dg.datagrid('options');
        var preIdx = -1;

        var cell = dg.datagrid('cell');
        if (cell) {
            var input = dg.datagrid('input', cell);
            if (input) {
                if (cell.index == param.index && cell.field == param.field) {
                    dg.datagrid('gotoCell', cell);
                    input.focus();
                    return;
                } else {
                    if (dg.datagrid('validateRow', cell.index)) {
                        preIdx = cell.index;
                        dg.datagrid('endEdit', cell.index);
                    } else {
                        dg.datagrid('gotoCell', cell);
                        input.focus();
                        return;
                    }
                }
            }
        }

        if (!param.onCancelPre(param.index, preIdx, param.field)) {
            return false;
        }

        var fields = dg.datagrid('getColumnFields', true).concat(dg.datagrid('getColumnFields'));
        $.map(fields, function (field) {
            var col = dg.datagrid('getColumnOption', field);
            col.editor1 = col.editor;
            if (field != param.field) {
                col.editor = null;
            }
        });

        // dg.datagrid('endEdit', param.index);
        dg.datagrid('beginEdit', param.index);
        var input = dg.datagrid('input', param);
        if (input) {
            dg.datagrid('gotoCell', param);
            input.focus();
            opts.onCellEdit.call(target, param.index, param.field, param.value);
        } else {
            dg.datagrid('cancelEdit', param.index);
            dg.datagrid('gotoCell', param);
        }

        $.map(fields, function (field) {
            var col = dg.datagrid('getColumnOption', field);
            col.editor = col.editor1;
        });
    }

    function enableCellEditing(target, fun) {
        var dg = $(target);
        var opts = dg.datagrid('options');
        var panel = dg.datagrid('getPanel');
        panel.attr('tabindex', 1).css('outline-style', 'none').unbind('.cellediting').bind('keydown.cellediting',function (e) {
            var h = opts.navHandler[e.keyCode];
            if (h) {
                return h.call(target, e);
            }
        }).bind('keypress.cellediting', function (e) {
                return opts.navHandler['keypress'].call(target, e);
            });

        opts.isRtl = dg.datagrid('getPanel').css('direction').toLowerCase() == 'rtl';
        opts.oldOnClickCell = opts.onClickCell;
        opts.oldOnDblClickCell = opts.onDblClickCell;
        if (opts.clickToEdit) {
            opts.onClickCell = function (index, field, value) {
                $(this).datagrid('editCell', {index: index, field: field, onCancelPre: fun});
                opts.oldOnClickCell.call(this, index, field, value);
            }
        }
        if (opts.dblclickToEdit) {
            opts.onDblClickCell = function (index, field, value) {
                $(this).datagrid('editCell', {index: index, field: field, onCancelPre: fun});
                opts.oldOnDblClickCell.call(this, index, field, value);
            }
        }
        opts.OldOnBeforeSelect = opts.onBeforeSelect;
        opts.onBeforeSelect = function () {
            return false;
        };
        dg.datagrid('clearSelections')
    }

    function disableCellEditing(target) {
        var dg = $(target);
        var opts = dg.datagrid('options');
        opts.onClickCell = opts.oldOnClickCell || opts.onClickCell;
        opts.onDblClickCell = opts.oldOnDblClickCell || opts.onDblClickCell;
        opts.onBeforeSelect = opts.OldOnBeforeSelect || opts.onBeforeSelect;
        dg.datagrid('getPanel').unbind('.cellediting').find('td.datagrid-row-selected').removeClass('datagrid-row-selected');
    }


    $.extend($.fn.datagrid.methods, {
        editCell: function (jq, param) {
            return jq.each(function () {
                editCell(this, param);
            });
        },
        isEditing: function (jq, index) {
            var opts = $.data(jq[0], 'datagrid').options;
            var tr = opts.finder.getTr(jq[0], index);
            return tr.length && tr.hasClass('datagrid-row-editing');
        },
        gotoCell: function (jq, param) {
            return jq.each(function () {
                gotoCell(this, param);
            });
        },
        enableCellEditing: function (jq, fun) {
            return jq.each(function () {
                enableCellEditing(this, fun);
            });
        },
        disableCellEditing: function (jq) {
            return jq.each(function () {
                disableCellEditing(this);
            });
        },
        input: function (jq, param) {
            if (!param) {
                return null;
            }
            var ed = jq.datagrid('getEditor', param);
            if (ed) {
                var t = $(ed.target);
                if (t.hasClass('textbox-f')) {
                    t = t.textbox('textbox');
                }
                return t;
            } else {
                return null;
            }
        },
        cell: function (jq) {		// get current cell info {index,field}
            return getCurrCell(jq[0]);
        }
    });

})(jQuery);